package ru.gilko.rental.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.gilko.rental.domain.Rental;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RentalRepository extends CrudRepository<Rental, Integer> {
    List<Rental> findAllByUsername(String username);
    Optional<Rental> findByUsernameAndRentalUid(String username, UUID rentalUid);
}
