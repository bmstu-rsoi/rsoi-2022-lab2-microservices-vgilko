package ru.gilko.rentalimpl.service.api;

import ru.gilko.rentalapi.dto.RentalInDto;
import ru.gilko.rentalapi.dto.RentalOutDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RentalService {
    RentalOutDto createRental(String username, RentalInDto rentalInDto);

    List<RentalOutDto> getRentals(String username);

    Optional<RentalOutDto> getRental(UUID rentalUid, String username);

    void cancelRental(UUID rentalUid, String username);

    void finishRental(UUID rentalUid, String username);
}
