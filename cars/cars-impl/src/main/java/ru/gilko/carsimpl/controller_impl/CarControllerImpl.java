package ru.gilko.carsimpl.controller_impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.gilko.carsapi.controller.CarController;
import ru.gilko.carsapi.dto.CarOutDto;
import ru.gilko.carsimpl.service.api.CarService;

import java.nio.channels.ReadPendingException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@Slf4j
public class CarControllerImpl implements CarController {

    private final CarService carService;

    public CarControllerImpl(CarService carService) {
        this.carService = carService;
    }

    @Override
    public ResponseEntity<Page<CarOutDto>> getCars(boolean showAll, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        log.info("Request for reading all cars. Show reserved = {}. Page request = {}", showAll, pageRequest);

        Page<CarOutDto> cars = carService.getCars(showAll, pageRequest);

        return ResponseEntity.ok(cars);
    }

    @Override
    public CarOutDto getCar(UUID carId) {
        log.info("Request for reading car {}", carId);

        return carService.getCar(carId);
    }

    @Override
    public ResponseEntity<List<CarOutDto>> getCars(Set<UUID> carUids) {
        log.info("Request for reading cars with uids {}", carUids);

        return ResponseEntity.ok(carService.getCars(carUids));
    }

    @Override
    public ResponseEntity<?> changeAvailability(UUID carId) {
        log.info("Request for changing availability for car {}", carId);

        carService.changeAvailability(carId);

        return ResponseEntity.ok().build();
    }
}
