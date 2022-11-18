package ru.gilko.paymentapi.feign;

import ru.gilko.paymentapi.constants.ControllerUrls;
import ru.gilko.paymentapi.dto.PaymentOutDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient("payment")
public interface PaymentFeign {
    @PostMapping(path = ControllerUrls.PAYMENT_BASE_URL)
    PaymentOutDto createPayment(@RequestBody int price);

    @DeleteMapping(path = ControllerUrls.PAYMENT_WITH_ID_URL)
    void cancelPayment(@PathVariable UUID paymentUid);
}
