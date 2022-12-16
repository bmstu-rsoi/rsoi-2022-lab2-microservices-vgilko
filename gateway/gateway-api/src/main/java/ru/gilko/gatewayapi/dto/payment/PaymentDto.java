package ru.gilko.gatewayapi.dto.payment;

import lombok.Data;
import ru.gilko.paymentapi.constants.enums.PaymentStatus;

import java.util.UUID;

@Data
public class PaymentDto {
    private UUID paymentUid;
    private PaymentStatus status;
    private int price;
}
