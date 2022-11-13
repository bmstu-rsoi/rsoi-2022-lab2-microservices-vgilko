package ru.gilko.payment.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gilko.payment.constants.enums.PaymentStatus;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "payment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private UUID paymentUid;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    private int price;
}
