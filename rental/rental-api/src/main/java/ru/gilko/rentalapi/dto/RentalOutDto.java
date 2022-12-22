package ru.gilko.rentalapi.dto;

import lombok.Data;
import ru.gilko.rentalapi.constants.enums.RentalStatus;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class RentalOutDto {
    private UUID rentalUid;
    private String username;
    private UUID paymentUid;
    private UUID carUid;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private RentalStatus status;
}
