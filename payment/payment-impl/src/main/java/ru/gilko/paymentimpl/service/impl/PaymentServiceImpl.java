package ru.gilko.paymentimpl.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.gilko.paymentapi.constants.enums.PaymentStatus;
import ru.gilko.paymentapi.dto.PaymentOutDto;
import ru.gilko.paymentapi.exception.NoSuchEntityException;
import ru.gilko.paymentimpl.domain.Payment;
import ru.gilko.paymentimpl.repository.PaymentRepository;
import ru.gilko.paymentimpl.service.api.PaymentService;

import java.util.List;
import java.util.Optional;
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
    public List<PaymentOutDto> getPayments(List<UUID> paymentsUids) {
        List<Payment> payments = paymentRepository.findByPaymentUidIn(paymentsUids);

        return payments.stream()
                .map(payment -> modelMapper.map(payment, PaymentOutDto.class))
                .toList();
    }

    @Override
    public Optional<PaymentOutDto> getPayment(UUID paymentUid) {
        Optional<Payment> payment = paymentRepository.findByPaymentUid(paymentUid);

        return payment.map(optionalPayment ->
                modelMapper.map(optionalPayment, PaymentOutDto.class));
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
