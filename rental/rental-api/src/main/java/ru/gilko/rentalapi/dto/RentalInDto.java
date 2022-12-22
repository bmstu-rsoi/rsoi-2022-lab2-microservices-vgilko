package ru.gilko.rentalapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
public class RentalInDto {
    private UUID carUid;
    private UUID paymentUid;
    private LocalDate dateFrom;
    private LocalDate dateTo;
}