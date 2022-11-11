package ru.gilko.cars.service.api;

import org.springframework.data.domain.Pageable;
import ru.gilko.cars.dto.CarOutDto;
import ru.gilko.cars.dto.PageableCollectionOutDto;

import java.util.UUID;

public interface CarService {
    PageableCollectionOutDto<CarOutDto> getCars(boolean showAll, Pageable pageable);

    void changeAvailability(UUID carId);
}

