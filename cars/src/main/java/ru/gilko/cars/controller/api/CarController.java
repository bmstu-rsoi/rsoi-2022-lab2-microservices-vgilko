package ru.gilko.cars.controller.api;

import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gilko.cars.constants.ControllerUrl;
import ru.gilko.cars.dto.CarOutDto;
import ru.gilko.cars.dto.PageableCollectionOutDto;

import java.util.UUID;

public interface CarController {
    @GetMapping(path = ControllerUrl.CARS_URL)
    ResponseEntity<PageableCollectionOutDto<CarOutDto>> getCars(@RequestParam @DefaultValue("false") boolean showAll,
                                                                @RequestParam @DefaultValue("0") int page,
                                                                @RequestParam @DefaultValue("100") int size);

    @PutMapping(path = ControllerUrl.CONCRETE_CAR_URL)
    ResponseEntity<?> changeAvailability(@PathVariable UUID carId);
}
