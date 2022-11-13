package ru.gilko.gateway.service.api;

import ru.gilko.cars.dto.PageableCollectionOutDto;
import ru.gilko.gateway.dto.CarDto;

public interface GatewayService {
    PageableCollectionOutDto<CarDto> getAllCars(boolean showAll, int page, int size);
}
