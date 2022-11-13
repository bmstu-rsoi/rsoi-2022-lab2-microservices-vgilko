package ru.gilko.carsimpl.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.gilko.carsapi.controller.api.CarController;
import ru.gilko.carsapi.dto.CarOutDto;
import ru.gilko.carsapi.dto.PageableCollectionOutDto;
import ru.gilko.carsimpl.service.api.CarService;

import java.util.UUID;

@RestController
@Slf4j
public class CarControllerImpl implements CarController {

    private final CarService carService;

    public CarControllerImpl(CarService carService) {
        this.carService = carService;
    }

    @Override
    public ResponseEntity<PageableCollectionOutDto<CarOutDto>> getCars(boolean showAll, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        log.info("Request for reading all cars. Show reserved = {}. Page request = {}", showAll, pageRequest);

        PageableCollectionOutDto<CarOutDto> cars = carService.getCars(showAll, pageRequest);

        return ResponseEntity.ok(cars);
    }

    @Override
    public ResponseEntity<?> changeAvailability(UUID carId) {
        log.info("Request for changing availability for car {}", carId);

        carService.changeAvailability(carId);

        return ResponseEntity.ok().build();
    }
}
