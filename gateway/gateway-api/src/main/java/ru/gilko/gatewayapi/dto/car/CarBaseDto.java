package ru.gilko.gatewayapi.dto.car;

import lombok.Data;

import java.util.UUID;

@Data
public class CarBaseDto {
    private UUID carUid;
    private String brand;
    private String model;
    private String registrationNumber;
}
