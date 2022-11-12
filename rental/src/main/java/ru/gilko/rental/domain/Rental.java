package ru.gilko.rental.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gilko.rental.constants.enums.Status;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    @Enumerated(EnumType.STRING)
    private Status status;
}
