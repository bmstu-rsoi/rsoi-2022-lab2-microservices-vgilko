package ru.gilko.payment.dto;

import lombok.Data;
import ru.gilko.payment.constants.enums.PaymentStatus;

import java.util.UUID;

@Data
public class PaymentOutDto {
    private UUID paymentUid;
    private PaymentStatus status;
    private int price;
}
