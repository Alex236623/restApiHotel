package com.hotel.service;

import com.hotel.domain.Guest;
import com.hotel.domain.Reservation;
import com.hotel.dto.ReservationDto;
import com.hotel.repository.ReservationRepository;
import com.hotel.exception.ResourceNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public List<ReservationDto> getAllReservations() {
        return reservationRepository.findAll()
                .stream()
                .map(reservation -> ReservationDto.builder()
                        .id(reservation.getId())
                        .startDate(reservation.getStartDate())
                        .endDate(reservation.getEndDate())
                        .room(reservation.getRoom())
                        .guests(reservation.getGuests().stream()
                                .map(Guest::guestShortCard)
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());
    }

    public ReservationDto getReservationById(Long id) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            return ReservationDto.builder()
                    .id(reservation.getId())
                    .startDate(reservation.getStartDate())
                    .endDate(reservation.getEndDate())
                    .room(reservation.getRoom())
                    .guests(reservation.getGuests().stream()
                            .map(Guest::guestShortCard)
                            .collect(Collectors.toList()))
                    .build();
        } else {
            throw new ResourceNotFoundException("Reservation", "id", id);
        }
    }

    public Reservation addReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Reservation updateReservation(Long id, Reservation reservation) {
        Reservation existingReservation = reservationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Reservation", "id", id));
        existingReservation.setStartDate(reservation.getStartDate());
        existingReservation.setEndDate(reservation.getEndDate());
        existingReservation.setRoom(reservation.getRoom());
        return reservationRepository.save(existingReservation);
    }

    public void deleteReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Reservation", "id", id));
        reservationRepository.delete(reservation);
    }


}