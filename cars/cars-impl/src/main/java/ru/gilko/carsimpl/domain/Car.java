package ru.gilko.carsimpl.domain;

import lombok.Data;
import ru.gilko.carsapi.constants.enums.CarType;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "cars")
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private UUID carUid;
    private String brand;
    private String model;
    private String registrationNumber;
    private int power;
    @Enumerated(EnumType.STRING)
    private CarType type;
    private int price;
    private boolean availability;
}
