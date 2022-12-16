package ru.gilko.paymentimpl.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.gilko.paymentimpl.domain.Payment;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Integer> {
    Optional<Payment> findByPaymentUid(UUID paymentUid);
    List<Payment> findByPaymentUidIn(List<UUID> paymentUids);
}
