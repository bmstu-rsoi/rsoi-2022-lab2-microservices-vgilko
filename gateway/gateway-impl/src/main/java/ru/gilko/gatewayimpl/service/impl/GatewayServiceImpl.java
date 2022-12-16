package ru.gilko.gatewayimpl.service.impl;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ru.gilko.carsapi.dto.CarOutDto;
import ru.gilko.carsapi.feign.CarFeign;
import ru.gilko.gatewayapi.dto.CarBookDto;
import ru.gilko.gatewayapi.dto.car.CarBaseDto;
import ru.gilko.gatewayapi.dto.car.CarDto;
import ru.gilko.gatewayapi.dto.payment.PaymentDto;
import ru.gilko.gatewayapi.dto.rental.RentalCreationOutDto;
import ru.gilko.gatewayapi.dto.rental.RentalDto;
import ru.gilko.gatewayapi.dto.wrapper.PageableCollectionOutDto;
import ru.gilko.gatewayapi.exception.InvalidOperationException;
import ru.gilko.gatewayapi.exception.NoSuchEntityException;
import ru.gilko.gatewayimpl.service.api.GatewayService;
import ru.gilko.paymentapi.dto.PaymentOutDto;
import ru.gilko.paymentapi.feign.PaymentFeign;
import ru.gilko.rentalapi.dto.RentalInDto;
import ru.gilko.rentalapi.dto.RentalOutDto;
import ru.gilko.rentalapi.feign.RentalFeign;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@Slf4j
public class GatewayServiceImpl implements GatewayService {
    private final CarFeign carFeign;
    private final RentalFeign rentalFeign;
    private final PaymentFeign paymentFeign;

    private final ModelMapper modelMapper;

    public GatewayServiceImpl(CarFeign carFeign, RentalFeign rentalFeign, PaymentFeign paymentFeign, ModelMapper modelMapper) {
        this.carFeign = carFeign;
        this.rentalFeign = rentalFeign;
        this.paymentFeign = paymentFeign;
        this.modelMapper = modelMapper;
    }

    @Override
    public PageableCollectionOutDto<CarDto> getAllCars(boolean showAll, int page, int size) {
        Page<CarOutDto> carOutDtos = carFeign.getCars(showAll, page, size);

        log.info("Received {} entities from car service", carOutDtos.getTotalElements());

        return mapToPageCollectionOutDto(carOutDtos, CarDto.class);
    }

    @Override
    public List<RentalDto> getRental(String username) {
        List<RentalOutDto> rentals = rentalFeign.getRentals(username);

        List<UUID> paymentsUids = new LinkedList<>();
        List<UUID> carUids = new LinkedList<>();
        rentals.forEach(rentalOutDto -> {
            paymentsUids.add(rentalOutDto.getPaymentUid());
            carUids.add(rentalOutDto.getCarUid());
        });

        Map<UUID, PaymentOutDto> payments = paymentFeign.getPayments(paymentsUids)
                .stream()
                .collect(Collectors.toMap(PaymentOutDto::getPaymentUid, Function.identity()));

        Map<UUID, CarOutDto> cars = carFeign.getCars(carUids)
                .stream()
                .collect(Collectors.toMap(CarOutDto::getCarUid, Function.identity()));

        return rentals.stream()
                .map(rentalOutDto -> buildOutDto(rentalOutDto, payments, cars))
                .toList();
    }

    @Override
    public RentalDto getRental(String username, UUID rentalUid) {
        RentalOutDto rental = rentalFeign.getRental(rentalUid, username);
        log.info("Get rental form rental service: {}", rental);

        CarOutDto car = carFeign.getCar(rental.getCarUid());
        PaymentOutDto payment = paymentFeign.getPayment(rental.getPaymentUid());

        RentalDto mappedRental = modelMapper.map(rental, RentalDto.class);
        mappedRental.setCar(modelMapper.map(car, CarBaseDto.class));
        mappedRental.setPayment(modelMapper.map(payment, PaymentDto.class));

        return mappedRental;
    }

    @Override
    public RentalCreationOutDto bookCar(String userName, CarBookDto carBookDto) {
        CarOutDto car = carFeign.getCar(carBookDto.getCarUid());

        if (!car.isAvailable()) {
            log.error("Trying to book not available car {}", car.getCarUid());
            throw new InvalidOperationException("Car %s is not available".formatted(car.getCarUid()));
        }

        changeCarAvailability(carBookDto.getCarUid());
        PaymentOutDto payment = createPayment(carBookDto, car.getPrice());
        RentalOutDto rental = createRental(userName, carBookDto, payment);

        RentalCreationOutDto rentalCreationOutDto = modelMapper.map(rental, RentalCreationOutDto.class);
        rentalCreationOutDto.setPayment(modelMapper.map(payment, PaymentDto.class));
        rentalCreationOutDto.setCarUid(carBookDto.getCarUid());

        return rentalCreationOutDto;
    }

    @Override
    public void finishRental(String username, UUID rentalUid) {
        try {
            rentalFeign.finishRental(rentalUid, username);
        } catch (FeignException.NotFound e) {
            log.info("Trying to finish non-existing rental: username = {}, rentalUid = {}", username, rentalUid);

            throw new NoSuchEntityException(e.getMessage());
        }
    }

    @Override
    public void cancelRental(String username, UUID rentalUid) {
        try {
            RentalOutDto rental = rentalFeign.getRental(rentalUid, username);

            rentalFeign.cancelRental(rentalUid, username);
            paymentFeign.cancelPayment(rental.getPaymentUid());
            carFeign.changeAvailability(rental.getCarUid());
        } catch (FeignException.NotFound e) {
            log.info("Trying to cancel non-existing rental: username = {}, rentalUid = {}", username, rentalUid);

            throw new NoSuchEntityException(e.getMessage());
        }
    }

    private void changeCarAvailability(UUID carId) {
        try {
            carFeign.changeAvailability(carId);
        } catch (FeignException.NotFound e) {
            log.info("Trying to change availability for non existing car {}", carId);
            throw new NoSuchEntityException("There is no car with id = %s".formatted(carId));
        }
    }

    private PaymentOutDto createPayment(CarBookDto carBookDto, int carRentalPrice) {
        int amountRentalDays = (int) calculateAmountRentalDays(carBookDto);
        int totalPrice = amountRentalDays * carRentalPrice;

        return paymentFeign.createPayment(totalPrice);
    }

    private long calculateAmountRentalDays(CarBookDto carBookDto) {
        long totalRentalDays = DAYS.between(carBookDto.getDateFrom(), carBookDto.getDateTo());

        if (totalRentalDays < 0) {
            log.error("Trying to create rental with invalid dates DateFrom {}, DateTo {}",
                    carBookDto.getDateFrom(),
                    carBookDto.getDateTo());
            throw new InvalidOperationException("Invalid car rental dates. DateTo should be after DateFrom");
        }

        return totalRentalDays;
    }

    private RentalOutDto createRental(String username, CarBookDto carBookDto, PaymentOutDto payment) {
        RentalInDto rentalInDto = new RentalInDto(
                carBookDto.getCarUid(), payment.getPaymentUid(), carBookDto.getDateFrom(), carBookDto.getDateTo());

        return rentalFeign.createRental(username, rentalInDto);
    }

    private RentalDto buildOutDto(RentalOutDto rentalOutDto, Map<UUID, PaymentOutDto> payments, Map<UUID, CarOutDto> cars) {
        RentalDto rental = modelMapper.map(rentalOutDto, RentalDto.class);

        PaymentOutDto payment = payments.get(rentalOutDto.getPaymentUid());
        PaymentDto paymentDto = modelMapper.map(payment, PaymentDto.class);

        CarOutDto car = cars.get(rentalOutDto.getCarUid());
        CarDto carDto = modelMapper.map(car, CarDto.class);

        rental.setPayment(paymentDto);
        rental.setCar(carDto);

        return rental;
    }

    private <T> PageableCollectionOutDto<T> mapToPageCollectionOutDto(Page<CarOutDto> page, Class<T> destinationClass) {
        Page<T> mappedPage = page.map(car -> modelMapper.map(car, destinationClass));

        return buildPageCollectionOutDto(mappedPage);
    }

    private <T> PageableCollectionOutDto<T> buildPageCollectionOutDto(Page<T> page) {
        return new PageableCollectionOutDto<>(page.getContent(), page.getNumber(), page.getSize(), page.getTotalPages());
    }
}
