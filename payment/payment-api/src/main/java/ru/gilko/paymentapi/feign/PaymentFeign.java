package ru.gilko.paymentapi.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.gilko.paymentapi.constants.ControllerUrls;
import ru.gilko.paymentapi.dto.PaymentOutDto;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "payment", path = "/payment", url = "${feign.payment.url}")
public interface PaymentFeign {
    @GetMapping(path = ControllerUrls.PAYMENT_BASE_URL)
    List<PaymentOutDto> getPayments(@RequestParam List<UUID> paymentsUids);

    @GetMapping(path = ControllerUrls.PAYMENT_WITH_ID_URL)
    PaymentOutDto getPayment(@PathVariable UUID paymentUid);

    @PostMapping(path = ControllerUrls.PAYMENT_BASE_URL)
    PaymentOutDto createPayment(@RequestBody int price);

    @DeleteMapping(path = ControllerUrls.PAYMENT_WITH_ID_URL)
    void cancelPayment(@PathVariable UUID paymentUid);
}
