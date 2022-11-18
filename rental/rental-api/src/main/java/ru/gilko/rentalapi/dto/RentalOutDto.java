package ru.gilko.rentalapi.dto;

import lombok.Data;
import ru.gilko.rentalapi.constants.enums.Status;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class RentalOutDto {
    private UUID rentalUid;
    private String username;
    private UUID paymentUid;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    private Status status;
}
