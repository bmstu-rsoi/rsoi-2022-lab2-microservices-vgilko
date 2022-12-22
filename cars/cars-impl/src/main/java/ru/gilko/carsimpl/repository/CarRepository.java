package ru.gilko.carsimpl.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gilko.carsimpl.domain.Car;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface CarRepository extends JpaRepository<Car, UUID> {
    Page<Car> findAllByAvailabilityIsTrue(Pageable pageable);

    Optional<Car> findByCarUid(UUID carId);
    List<Car> findAllByCarUidIn(Set<UUID> carUids);
}
