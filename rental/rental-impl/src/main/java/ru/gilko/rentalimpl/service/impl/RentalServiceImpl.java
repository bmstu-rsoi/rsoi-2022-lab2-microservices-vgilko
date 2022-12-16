package ru.gilko.rentalimpl.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.gilko.rentalapi.constants.enums.RentalStatus;
import ru.gilko.rentalapi.dto.RentalInDto;
import ru.gilko.rentalapi.dto.RentalOutDto;
import ru.gilko.rentalapi.exceptions.NoSuchEntityException;
import ru.gilko.rentalimpl.domain.Rental;
import ru.gilko.rentalimpl.repository.RentalRepository;
import ru.gilko.rentalimpl.service.api.RentalService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class RentalServiceImpl implements RentalService {
    private final RentalRepository rentalRepository;
    private final ModelMapper modelMapper;

    public RentalServiceImpl(RentalRepository rentalRepository, ModelMapper modelMapper) {
        this.rentalRepository = rentalRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public RentalOutDto createRental(String username, RentalInDto rentalInDto) {
        Rental rental = buildRentalForCreation(username, rentalInDto);

        Rental savedRental = rentalRepository.save(rental);
        log.info("Rental was created {}", savedRental);

        return modelMapper.map(savedRental, RentalOutDto.class);
    }

    private Rental buildRentalForCreation(String username, RentalInDto rentalInDto) {
        Rental rental = modelMapper.map(rentalInDto, Rental.class);
        rental.setRentalUid(UUID.randomUUID());
        rental.setUsername(username);
        rental.setStatus(RentalStatus.IN_PROGRESS);
        return rental;
    }

    public List<RentalOutDto> getRentals(String username) {
        List<Rental> rentals = rentalRepository.findAllByUsername(username);

        log.info("Get {} rentals for username {}", rentals.size(), username);

        return rentals.stream()
                .map(rental -> modelMapper.map(rental, RentalOutDto.class))
                .toList();
    }

    @Override
    public Optional<RentalOutDto> getRental(UUID rentalUid, String username) {
        Optional<Rental> rental = rentalRepository.findByUsernameAndRentalUid(username, rentalUid);

        rental.ifPresent(value -> log.info("Get rental: {}", value));

        return rental.map(optionalRental -> modelMapper.map(rental.get(), RentalOutDto.class));
    }

    @Override
    public void cancelRental(UUID rentalUid, String username) {
        changeRentalStatus(username, rentalUid, RentalStatus.CANCELED);
    }

    @Override
    public void finishRental(UUID rentalUid, String username) {
        changeRentalStatus(username, rentalUid, RentalStatus.FINISHED);
    }

    private void changeRentalStatus(String username, UUID rentalUid, RentalStatus finished) {
        Optional<Rental> rental = rentalRepository.findByUsernameAndRentalUid(username, rentalUid);

        if (rental.isEmpty()) {
            log.error("Requesting rental {} for user {} doesn't exist", rentalUid, username);
            throw new NoSuchEntityException("There is no rental %s for user %s".formatted(rentalUid, username));
        }

        Rental unpackedRental = rental.get();
        unpackedRental.setStatus(finished);

        Rental updatedRental = rentalRepository.save(unpackedRental);
        log.info("Rental {} status was changed. New status: {}", rentalUid, updatedRental.getStatus());
    }
}
