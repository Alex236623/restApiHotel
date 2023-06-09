package com.hotel.repository;

import com.hotel.domain.Room;
import com.hotel.domain.Reservation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    boolean existsByRoomAndStartDateBetweenOrRoomAndEndDateBetween(Room room1, LocalDate startDate1, LocalDate endDate1, Room room2, LocalDate startDate2, LocalDate endDate2);
}