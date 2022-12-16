package ru.gilko.gatewayapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gilko.gatewayapi.constants.ControllerUrls;
import ru.gilko.gatewayapi.dto.CarBookDto;
import ru.gilko.gatewayapi.dto.car.CarDto;
import ru.gilko.gatewayapi.dto.rental.RentalCreationOutDto;
import ru.gilko.gatewayapi.dto.rental.RentalDto;
import ru.gilko.gatewayapi.dto.wrapper.PageableCollectionOutDto;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static ru.gilko.gatewayapi.constants.ProjectConstants.USERNAME_HEADER;

public interface GatewayController {
    @GetMapping(ControllerUrls.CARS_URL)
    PageableCollectionOutDto<CarDto> getAllCars(@RequestParam boolean showAll,
                                                @RequestParam int page,
                                                @RequestParam int size);

    @GetMapping(ControllerUrls.RENTAL_URL)
    ResponseEntity<List<RentalDto>> getRental(@RequestHeader(USERNAME_HEADER) String username);

    @GetMapping(ControllerUrls.RENTAL_URL_WITH_ID)
    ResponseEntity<RentalDto> getRental(@RequestHeader(USERNAME_HEADER) String username,
                                        @PathVariable UUID rentalUid);

    @PostMapping(ControllerUrls.RENTAL_URL)
    ResponseEntity<RentalCreationOutDto> bookCar(@RequestHeader(USERNAME_HEADER) String userName,
                                                 @RequestBody @Valid CarBookDto carBookDto);

    @PostMapping(ControllerUrls.RENTAL_URL_FINISH)
    ResponseEntity<?> finishRental(@RequestHeader(USERNAME_HEADER) String username,
                                   @PathVariable UUID rentalUid);

    @DeleteMapping(ControllerUrls.RENTAL_URL_WITH_ID)
    ResponseEntity<?> cancelRental(@RequestHeader(USERNAME_HEADER) String username,
                                   @PathVariable UUID rentalUid);

}
