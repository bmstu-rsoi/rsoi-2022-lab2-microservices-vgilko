package ru.gilko.payment.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.gilko.payment.constants.ControllerUrls;
import ru.gilko.payment.dto.PaymentOutDto;

import java.util.UUID;

public interface PaymentController {
    @PostMapping(path = ControllerUrls.PAYMENT_BASE_URL)
    ResponseEntity<PaymentOutDto> createPayment(@RequestBody int price);

    @DeleteMapping(path = ControllerUrls.PAYMENT_WITH_ID_URL)
    ResponseEntity<?> cancelPayment(@PathVariable UUID paymentUid);
}
