package ru.gilko.gateway.controller.api;

import org.springframework.web.bind.annotation.RequestParam;
import ru.gilko.cars.dto.PageableCollectionOutDto;
import ru.gilko.gateway.dto.CarDto;

public interface GatewayController {
    PageableCollectionOutDto<CarDto> getAllCars(@RequestParam boolean showAll,
                                                @RequestParam int page,
                                                @RequestParam int size);
}
