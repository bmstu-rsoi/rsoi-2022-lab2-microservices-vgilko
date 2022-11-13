package ru.gilko.carsapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gilko.carsapi.constants.enums.CarType;

import java.util.UUID;

@Data
@NoArgsConstructor
public class CarOutDto {
    private UUID carUid;
    private String brand;
    private String model;
    private String registrationNumber;
    private int power;
    private CarType type;
    private int price;
    private boolean available;
}
