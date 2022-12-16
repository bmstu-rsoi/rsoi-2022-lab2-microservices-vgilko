package ru.gilko.gatewayapi.dto.rental;

import lombok.Data;
import ru.gilko.gatewayapi.dto.payment.PaymentDto;
import ru.gilko.rentalapi.constants.enums.RentalStatus;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class RentalCreationOutDto {
    private UUID rentalUid;
    private RentalStatus status;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private UUID carUid;
    private PaymentDto payment;
}
