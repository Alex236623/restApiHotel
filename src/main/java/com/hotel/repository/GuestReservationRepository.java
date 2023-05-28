package com.hotel.repository;

import com.hotel.domain.GuestReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestReservationRepository extends JpaRepository<GuestReservation,Long> {
}
