package com.hotel.service;

import com.hotel.domain.Reservation;
import com.hotel.repository.ReservationMySqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationMySqlRepository reservationRepository;

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(Long id) {
        return (Reservation) reservationRepository.findById(id);//.orElseThrow(() -> new ResourceNotFoundException("Reservation", "id", id));
    }

    public Reservation addReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Reservation updateReservation(Long id, Reservation reservation) {
        Reservation existingReservation = (Reservation) reservationRepository.findById(id);//.orElseThrow(() -> new ResourceNotFoundException("Reservation", "id", id));
        existingReservation.setStartDate(reservation.getStartDate());
        existingReservation.setEndDate(reservation.getEndDate());
        existingReservation.setRoom(reservation.getRoom());
        return reservationRepository.save(existingReservation);
    }

    public void deleteReservation(Long id) {
        Reservation reservation = (Reservation) reservationRepository.findById(id);//.orElseThrow(() -> new ResourceNotFoundException("Reservation", "id", id));
        reservationRepository.delete(reservation);
    }
}
