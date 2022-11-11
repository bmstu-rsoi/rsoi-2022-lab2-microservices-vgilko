package ru.gilko.cars.controller.feign;

import org.springframework.cloud.openfeign.FeignClient;
import ru.gilko.cars.controller.api.CarController;

@FeignClient(name = "cars")
public interface CarFeign extends CarController {
}
