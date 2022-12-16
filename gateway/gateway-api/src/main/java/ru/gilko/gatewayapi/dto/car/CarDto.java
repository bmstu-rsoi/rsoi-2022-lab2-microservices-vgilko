package ru.gilko.gatewayapi.dto.car;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CarDto extends CarBaseDto {
    private int power;
    private String type;
    private int price;
    boolean available;
}
