package ru.gilko.carsapi.feign;

import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gilko.carsapi.constants.ControllerUrl;
import ru.gilko.carsapi.dto.CarOutDto;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "cars", path = "/cars")
public interface CarFeign {
    @GetMapping(path = ControllerUrl.CARS_URL)
    Page<CarOutDto> getCars(@RequestParam @DefaultValue("false") boolean showAll,
                            @RequestParam @DefaultValue("0") int page,
                            @RequestParam @DefaultValue("100") int size);

    @GetMapping(path = ControllerUrl.CAR_URL_WITH_ID)
    CarOutDto getCar(@PathVariable UUID carId);

    @PostMapping(path = ControllerUrl.CARS_URL)
    List<CarOutDto> getCars(List<UUID> carUids);

    @PutMapping(path = ControllerUrl.CAR_URL_WITH_ID)
    ResponseEntity<?> changeAvailability(@PathVariable UUID carId);
}