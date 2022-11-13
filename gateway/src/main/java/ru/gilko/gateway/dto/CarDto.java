package ru.gilko.gateway.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CarDto {
    private UUID carUid;
    private String brand;
    private String model;
    private String registrationNumber;
    private int power;
    private String type;
    private int price;
    boolean available;
}
