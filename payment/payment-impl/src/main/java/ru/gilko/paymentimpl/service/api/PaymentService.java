package ru.gilko.paymentimpl.service.api;

import ru.gilko.paymentapi.dto.PaymentOutDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PaymentService {
    PaymentOutDto createPayment(int price);

    List<PaymentOutDto> getPayments(List<UUID> paymentsUids);

    void cancelPayment(UUID paymentUid);

    Optional<PaymentOutDto> getPayment(UUID paymentUid);
}
