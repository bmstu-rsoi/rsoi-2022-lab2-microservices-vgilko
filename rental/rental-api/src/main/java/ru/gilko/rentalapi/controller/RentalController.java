package ru.gilko.rentalapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gilko.rentalapi.dto.RentalInDto;
import ru.gilko.rentalapi.dto.RentalOutDto;
import ru.gilko.rentalapi.constants.ControllerUrls;
import ru.gilko.rentalapi.constants.ProjectConstants;

import java.util.List;
import java.util.UUID;


public interface RentalController {
    @PostMapping(path = ControllerUrls.RENTAL_BASE_URL)
    ResponseEntity<RentalOutDto> createRental(@RequestHeader(ProjectConstants.USERNAME_HEADER) String username, @RequestBody RentalInDto rentalInDto);

    @GetMapping(path = ControllerUrls.RENTAL_BASE_URL)
    ResponseEntity<List<RentalOutDto>> getRentals(@RequestHeader(ProjectConstants.USERNAME_HEADER) String username);

    @GetMapping(path = ControllerUrls.CONCRETE_RENTAL_URL)
    ResponseEntity<RentalOutDto> getRental(@PathVariable UUID rentalUid, @RequestHeader(ProjectConstants.USERNAME_HEADER) String username);

    @DeleteMapping(path = ControllerUrls.CONCRETE_RENTAL_URL)
    ResponseEntity<?> cancelRental(@PathVariable UUID rentalUid, @RequestHeader(ProjectConstants.USERNAME_HEADER) String username);

    @PostMapping(path = ControllerUrls.FINISH_RENTAL_URL)
    ResponseEntity<?> finishRental(@PathVariable UUID rentalUid, @RequestHeader(ProjectConstants.USERNAME_HEADER) String username);
}
