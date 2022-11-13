package ru.gilko.payment.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.gilko.payment.constants.enums.PaymentStatus;
import ru.gilko.payment.domain.Payment;
import ru.gilko.payment.dto.PaymentOutDto;
import ru.gilko.payment.exception.NoSuchEntityException;
import ru.gilko.payment.repository.PaymentRepository;
import ru.gilko.payment.service.api.PaymentService;

import java.util.UUID;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final ModelMapper modelMapper;

    public PaymentServiceImpl(PaymentRepository paymentRepository, ModelMapper modelMapper) {
        this.paymentRepository = paymentRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public PaymentOutDto createPayment(int price) {
        Payment payment = new Payment(null, UUID.randomUUID(), PaymentStatus.PAID, price);

        Payment savedPayment = paymentRepository.save(payment);

        log.info("Payment was created: {}", savedPayment);

        return modelMapper.map(savedPayment, PaymentOutDto.class);
    }

    @Override
    public void cancelPayment(UUID paymentUid) {
        Payment payment = paymentRepository.findByPaymentUid(paymentUid).orElseThrow(() -> {
            log.error("Deletion was aborted. There is no payment with id = {}", paymentUid);

            return new NoSuchEntityException("Unable to cancel payment " + paymentUid);
        });

        payment.setStatus(PaymentStatus.CANCELED);
        paymentRepository.save(payment);

        log.info("Payment {} status was updated to {}", paymentUid, payment.getStatus());
    }
}
