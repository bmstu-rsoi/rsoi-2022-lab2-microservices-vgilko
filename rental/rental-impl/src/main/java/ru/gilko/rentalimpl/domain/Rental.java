package ru.gilko.rentalimpl.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gilko.rentalapi.constants.enums.RentalStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "rental")
@Data
@NoArgsConstructor
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private UUID rentalUid;
    private String username;
    private UUID paymentUid;
    private UUID carUid;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    @Enumerated(EnumType.STRING)
    private RentalStatus status;
}
