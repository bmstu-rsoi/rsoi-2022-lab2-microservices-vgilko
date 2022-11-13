package ru.gilko.carsimpl.service.api;

import org.springframework.data.domain.Pageable;
import ru.gilko.carsapi.dto.CarOutDto;
import ru.gilko.carsapi.dto.PageableCollectionOutDto;

import java.util.UUID;

public interface CarService {
    PageableCollectionOutDto<CarOutDto> getCars(boolean showAll, Pageable pageable);

    void changeAvailability(UUID carId);
}

