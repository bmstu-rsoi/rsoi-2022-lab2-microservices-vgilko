package ru.gilko.gateway.controller.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import ru.gilko.cars.dto.PageableCollectionOutDto;
import ru.gilko.gateway.controller.api.GatewayController;
import ru.gilko.gateway.dto.CarDto;
import ru.gilko.gateway.service.api.GatewayService;

@RestController
@Slf4j
public class GatewayControllerImpl implements GatewayController {
    private final GatewayService gatewayService;

    public GatewayControllerImpl(GatewayService gatewayService) {
        this.gatewayService = gatewayService;
    }

    @Override
    public PageableCollectionOutDto<CarDto> getAllCars(boolean showAll, int page, int size) {
        log.info("Request for reading all cars. Show all = {}, page = {}, size = {}", showAll, page, size);


        return gatewayService.getAllCars(showAll, page, size);
    }
}
