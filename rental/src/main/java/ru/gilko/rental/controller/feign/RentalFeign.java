package ru.gilko.rental.controller.feign;

import org.springframework.cloud.openfeign.FeignClient;
import ru.gilko.rental.controller.api.RentalController;

@FeignClient("rental")
public interface RentalFeign extends RentalController {
}
