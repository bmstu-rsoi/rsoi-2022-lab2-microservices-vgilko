package ru.gilko.payment.service.api;

import ru.gilko.payment.dto.PaymentOutDto;

import java.util.UUID;

public interface PaymentService {
    PaymentOutDto createPayment(int price);
    void cancelPayment(UUID paymentUid);
}
