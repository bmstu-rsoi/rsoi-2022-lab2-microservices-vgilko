package ru.gilko.payment.controller.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.gilko.payment.controller.api.PaymentController;
import ru.gilko.payment.dto.PaymentOutDto;
import ru.gilko.payment.service.api.PaymentService;

import java.util.UUID;

@RestController
@Slf4j
public class PaymentControllerImpl implements PaymentController {
    private final PaymentService paymentService;

    public PaymentControllerImpl(PaymentService paymentService) {
        this.paymentService = paymentService;
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
