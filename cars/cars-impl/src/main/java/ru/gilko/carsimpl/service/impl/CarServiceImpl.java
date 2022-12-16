package ru.gilko.carsimpl.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.gilko.carsapi.dto.CarOutDto;
import ru.gilko.carsapi.exception.NoSuchEntityException;
import ru.gilko.carsimpl.domain.Car;
import ru.gilko.carsimpl.repository.CarRepository;
import ru.gilko.carsimpl.service.api.CarService;

import java.util.List;
import java.util.Set;
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
    public Page<CarOutDto> getCars(boolean showAll, Pageable pageable) {
        Page<Car> cars = showAll
                ? carRepository.findAll(pageable)
                : carRepository.findAllByAvailabilityIsTrue(pageable);


        return cars.map(car -> modelMapper.map(car, CarOutDto.class));
    }

    @Override
    public void changeAvailability(UUID carId) {
        Car car = carRepository.findByCarUid(carId).orElseThrow(() -> {
            log.info("Request for changing availability for non existing car {}", carId);

            return new NoSuchEntityException("There is no car with id = %s".formatted(carId));
        });

        car.setAvailability(!car.isAvailability());
        carRepository.save(car);

        log.info("Changed car {} availability to {}", carId, car.isAvailability());
    }

    @Override
    public List<CarOutDto> getCars(Set<UUID> carUids) {
        List<Car> cars = carRepository.findAllByCarUidIn(carUids);

        return cars.stream()
                .map(car -> modelMapper.map(car, CarOutDto.class))
                .toList();
    }

    @Override
    public CarOutDto getCar(UUID carId) {
        Car car = carRepository.findByCarUid(carId).orElseThrow(() -> {
            log.error("Requesting non existing car {}", carId);

            return new NoSuchEntityException("There is no car with id = %s".formatted(carId));
        });

        return modelMapper.map(car, CarOutDto.class);
    }
}
