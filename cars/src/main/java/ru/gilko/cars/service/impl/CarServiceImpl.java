package ru.gilko.cars.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.gilko.cars.domain.Car;
import ru.gilko.cars.dto.CarOutDto;
import ru.gilko.cars.dto.PageableCollectionOutDto;
import ru.gilko.cars.repository.CarRepository;
import ru.gilko.cars.service.api.CarService;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    private final ModelMapper modelMapper;

    public CarServiceImpl(CarRepository carRepository,
                          ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PageableCollectionOutDto<CarOutDto> getCars(boolean showAll, Pageable pageable) {
        Page<Car> cars = showAll
                ? carRepository.findAll(pageable)
                : carRepository.findAllByAvailabilityIsTrue(pageable);


        return mapToPageCollectionOutDto(cars, CarOutDto.class);
    }

    @Override
    public void changeAvailability(UUID carId) {
        Optional<Car> car = carRepository.findByCarUid(carId);
        if (car.isPresent()) {
            Car unpackedCar = car.get();
            unpackedCar.setAvailability(!unpackedCar.isAvailability());
            carRepository.save(unpackedCar);

            log.info("Changed car {} availability to {}", carId, unpackedCar.isAvailability());
        }
    }

    private <T> PageableCollectionOutDto<T> mapToPageCollectionOutDto(Page<Car> page, Class<T> destinationClass) {
        Page<T> mappedPage = page.map(car -> modelMapper.map(car, destinationClass));

        return buildPageCollectionOutDto(mappedPage);
    }

    private <T> PageableCollectionOutDto<T> buildPageCollectionOutDto(Page<T> page) {
        return new PageableCollectionOutDto<>(page.getContent(), page.getNumber(), page.getSize(), page.getTotalPages());
    }
}
