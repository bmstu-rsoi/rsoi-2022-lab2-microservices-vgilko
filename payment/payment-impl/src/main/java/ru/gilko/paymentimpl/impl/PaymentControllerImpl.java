package ru.gilko.paymentimpl.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.gilko.paymentapi.controller.PaymentController;
import ru.gilko.paymentapi.dto.PaymentOutDto;
import ru.gilko.paymentimpl.service.api.PaymentService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@Slf4j
public class PaymentControllerImpl implements PaymentController {
    private final PaymentService paymentService;

    public PaymentControllerImpl(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    public ResponseEntity<?> getPayments(List<UUID> paymentsUids) {
        log.info("Request for reading payments with uuids {}", paymentsUids);

        return ResponseEntity.ok(paymentService.getPayments(paymentsUids));
    }

    @Override
    public ResponseEntity<?> getPayment(UUID paymentUid) {
        log.info("Request for reading payment {}", paymentUid);

        Optional<PaymentOutDto> payment = paymentService.getPayment(paymentUid);

        return payment.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<PaymentOutDto> createPayment(int price) {
        log.info("Request for creating payment with price {}", price);

        return ResponseEntity.ok(paymentService.createPayment(price));
    }

    @Override
    public ResponseEntity<?> cancelPayment(UUID paymentUid) {
        log.info("Request for cancelling payment {}", paymentUid);

        paymentService.cancelPayment(paymentUid);
        return ResponseEntity.noContent().build();
    }
}
