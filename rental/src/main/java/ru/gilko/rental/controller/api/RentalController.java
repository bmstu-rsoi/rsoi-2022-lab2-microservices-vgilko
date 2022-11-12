package ru.gilko.rental.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gilko.rental.dto.RentalInDto;
import ru.gilko.rental.dto.RentalOutDto;

import java.util.List;
import java.util.UUID;

import static ru.gilko.rental.constants.ControllerUrls.*;
import static ru.gilko.rental.constants.ProjectConstants.USERNAME_HEADER;


public interface RentalController {
    @PostMapping(path = RENTAL_BASE_URL)
    ResponseEntity<RentalOutDto> createRental(@RequestHeader(USERNAME_HEADER) String username, @RequestBody RentalInDto rentalInDto);

    @GetMapping(path = RENTAL_BASE_URL)
    ResponseEntity<List<RentalOutDto>> getRentals(@RequestHeader(USERNAME_HEADER) String username);

    @GetMapping(path = CONCRETE_RENTAL_URL)
    ResponseEntity<RentalOutDto> getRental(@PathVariable UUID rentalUid, @RequestHeader(USERNAME_HEADER) String username);

    @DeleteMapping(path = CONCRETE_RENTAL_URL)
    ResponseEntity<?> cancelRental(@PathVariable UUID rentalUid, @RequestHeader(USERNAME_HEADER) String username);

    @PostMapping(path = FINISH_RENTAL_URL)
    ResponseEntity<?> finishRental(@PathVariable UUID rentalUid, @RequestHeader(USERNAME_HEADER) String username);
}
