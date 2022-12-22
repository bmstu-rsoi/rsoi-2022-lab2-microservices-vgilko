package ru.gilko.rentalimpl.controller_impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.gilko.rentalapi.constants.ControllerUrls;
import ru.gilko.rentalapi.constants.ProjectConstants;
import ru.gilko.rentalapi.controller.RentalController;
import ru.gilko.rentalapi.dto.RentalInDto;
import ru.gilko.rentalapi.dto.RentalOutDto;
import ru.gilko.rentalimpl.service.api.RentalService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@Slf4j
public class RentalControllerImpl implements RentalController {
    private final RentalService rentalService;

    public RentalControllerImpl(RentalService rentalService) {
        this.rentalService = rentalService;
    }


    @Override
    public ResponseEntity<RentalOutDto> createRental(String username, RentalInDto rentalInDto) {
        log.info("Request for creating rental for user {}", username);

        return ResponseEntity.ok(rentalService.createRental(username, rentalInDto));
    }

    @GetMapping(path = ControllerUrls.RENTAL_BASE_URL)
    public ResponseEntity<List<RentalOutDto>> getRentals(@RequestHeader(ProjectConstants.USERNAME_HEADER) String username) {
        log.info("Request for reading all rental of user {}", username);

        return ResponseEntity.ok(rentalService.getRentals(username));
    }

    @Override
    public ResponseEntity<RentalOutDto> getRental(UUID rentalUid, String username) {
        log.info("Request for reading rental {} of user {}", rentalUid, username);

        Optional<RentalOutDto> rental = rentalService.getRental(rentalUid, username);

        rental.ifPresent(rentalOutDto -> log.debug(rentalOutDto.toString()));

        return rental.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<?> cancelRental(UUID rentalUid, String username) {
        log.info("Request for cancelling rental {} for user {}", rentalUid, username);

        rentalService.cancelRental(rentalUid, username);

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<?> finishRental(UUID rentalUid, String username) {
        log.info("Request for finish rental {} for user {}", rentalUid, username);

        rentalService.finishRental(rentalUid, username);

        return ResponseEntity.noContent().build();
    }

}
