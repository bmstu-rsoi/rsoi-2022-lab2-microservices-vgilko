package ru.gilko.paymentimpl.service.api;

import ru.gilko.paymentapi.dto.PaymentOutDto;

import java.util.UUID;

public interface PaymentService {
    PaymentOutDto createPayment(int price);

    void cancelPayment(UUID paymentUid);
}
