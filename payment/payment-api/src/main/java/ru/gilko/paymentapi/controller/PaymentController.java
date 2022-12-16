package ru.gilko.paymentapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gilko.paymentapi.constants.ControllerUrls;
import ru.gilko.paymentapi.dto.PaymentOutDto;

import javax.naming.ldap.Control;
import java.util.List;
import java.util.UUID;

public interface PaymentController {
    @GetMapping(path = ControllerUrls.PAYMENT_BASE_URL)
    ResponseEntity<?> getPayments(@RequestParam List<UUID> paymentsUids);

    @GetMapping(path = ControllerUrls.PAYMENT_WITH_ID_URL)
    ResponseEntity<?> getPayment(@PathVariable UUID paymentUid);

    @PostMapping(path = ControllerUrls.PAYMENT_BASE_URL)
    ResponseEntity<PaymentOutDto> createPayment(@RequestBody int price);

    @DeleteMapping(path = ControllerUrls.PAYMENT_WITH_ID_URL)
    ResponseEntity<?> cancelPayment(@PathVariable UUID paymentUid);

}
