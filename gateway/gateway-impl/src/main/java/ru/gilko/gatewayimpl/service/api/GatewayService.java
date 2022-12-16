package ru.gilko.gatewayimpl.service.api;

import ru.gilko.gatewayapi.dto.CarBookDto;
import ru.gilko.gatewayapi.dto.car.CarDto;
import ru.gilko.gatewayapi.dto.rental.RentalCreationOutDto;
import ru.gilko.gatewayapi.dto.rental.RentalDto;
import ru.gilko.gatewayapi.dto.wrapper.PageableCollectionOutDto;

import java.util.List;
import java.util.UUID;

public interface GatewayService {
    PageableCollectionOutDto<CarDto> getAllCars(boolean showAll, int page, int size);

    List<RentalDto> getRental(String username);

    RentalDto getRental(String username, UUID rentalUid);

    RentalCreationOutDto bookCar(String userName, CarBookDto carBookDto);

    void finishRental(String username, UUID rentalUid);

    void cancelRental(String username, UUID rentalUid);
}
