package com.hotel.repository;

import com.hotel.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;




public interface ReservationRepository extends JpaRepository<Reservation, Long> {


}