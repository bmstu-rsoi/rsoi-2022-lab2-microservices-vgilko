package ru.gilko.carsimpl.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.gilko.carsapi.dto.CarOutDto;
import ru.gilko.carsimpl.domain.Car;
import ru.gilko.carsimpl.repository.CarRepository;
import ru.gilko.carsimpl.service.api.CarService;

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
    public Page<CarOutDto> getCars(boolean showAll, Pageable pageable) {
        Page<Car> cars = showAll
                ? carRepository.findAll(pageable)
                : carRepository.findAllByAvailabilityIsTrue(pageable);


        return cars.map(car -> modelMapper.map(car, CarOutDto.class));
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
}
