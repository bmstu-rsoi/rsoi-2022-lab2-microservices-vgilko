package ru.gilko.gatewayapi.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class CarBookDto {
    @NotNull(message = "CarUid should not be null")
    private UUID carUid;

    @NotNull(message = "DateFrom should not be null")

    private LocalDate dateTo;
    @NotNull(message = "DateTo should not be null")
    private LocalDate dateFrom;
}
