package ru.gilko.rentalapi.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.gilko.rentalapi.constants.ControllerUrls;
import ru.gilko.rentalapi.constants.ProjectConstants;
import ru.gilko.rentalapi.dto.RentalInDto;
import ru.gilko.rentalapi.dto.RentalOutDto;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "rental", path = "/rental", url = "${feign.rental.url}")
public interface RentalFeign {
    @PostMapping(path = ControllerUrls.RENTAL_BASE_URL)
    RentalOutDto createRental(@RequestHeader(ProjectConstants.USERNAME_HEADER) String username, @RequestBody RentalInDto rentalInDto);

    @GetMapping(path = ControllerUrls.RENTAL_BASE_URL)
    List<RentalOutDto> getRentals(@RequestHeader(ProjectConstants.USERNAME_HEADER) String username);

    @GetMapping(path = ControllerUrls.CONCRETE_RENTAL_URL)
    RentalOutDto getRental(@PathVariable UUID rentalUid, @RequestHeader(ProjectConstants.USERNAME_HEADER) String username);

    @DeleteMapping(path = ControllerUrls.CONCRETE_RENTAL_URL)
    void cancelRental(@PathVariable UUID rentalUid, @RequestHeader(ProjectConstants.USERNAME_HEADER) String username);

    @PostMapping(path = ControllerUrls.FINISH_RENTAL_URL)
    void finishRental(@PathVariable UUID rentalUid, @RequestHeader(ProjectConstants.USERNAME_HEADER) String username);
}
