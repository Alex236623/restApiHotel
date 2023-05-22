package com.hotel.repository;

import com.hotel.domain.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GuestRepository extends JpaRepository<Guest, Long> {
    Optional<Guest> findByFirstName(String name);

    Optional<Guest> findByPassportNumber(String passport);
}
