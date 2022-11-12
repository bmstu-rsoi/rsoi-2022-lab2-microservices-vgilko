package ru.gilko.rental.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class RentalInDto {
    private UUID carUid;
    private UUID paymentUid;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
}
