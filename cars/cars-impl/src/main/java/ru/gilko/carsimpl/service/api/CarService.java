package ru.gilko.carsimpl.service.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.gilko.carsapi.dto.CarOutDto;

import java.util.UUID;

public interface CarService {
    Page<CarOutDto> getCars(boolean showAll, Pageable pageable);

    void changeAvailability(UUID carId);
}

