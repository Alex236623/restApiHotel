package com.hotel.service;

import com.hotel.domain.Guest;
import com.hotel.domain.Reservation;
import com.hotel.domain.Room;
import com.hotel.dto.ReservationDto;
import com.hotel.repository.ReservationRepository;
import com.hotel.exception.ResourceNotFoundException;

import com.hotel.repository.RoomRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private RoomRepository roomRepository;

    public List<ReservationDto> getAllReservations() {
        return reservationRepository.findAll()
                .stream()
                .map(this::convertReservationDto)
                .collect(Collectors.toList());
    }

    public ReservationDto getReservationById(Long id) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            return convertReservationDto(reservation);
        } else {
            throw new ResourceNotFoundException("Reservation", "id", id);
        }
    }

    public Reservation addReservation(Reservation reservation) {
        Room room = Room.builder()
                .id(reservation.getRoom().getId())
                .price(reservation.getRoom().getPrice())
                .roomNumber(reservation.getRoom().getRoomNumber())
                .roomType(reservation.getRoom().getRoomType())
                .occupancy(reservation.getRoom().getOccupancy())
                .numberOfBeds(reservation.getRoom().getNumberOfBeds())
                .build();
        room = roomRepository.save(room);
        reservation.setRoom(room);
        return reservationRepository.save(reservation);
    }

    public ReservationDto updateReservation(Long id, ReservationDto updatedReservation) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);

        if (optionalReservation.isPresent()) {
            Reservation existingReservation = optionalReservation.get();
            existingReservation.setStartDate(updatedReservation.getStartDate());
            existingReservation.setEndDate(updatedReservation.getEndDate());

            Reservation reservation = reservationRepository.save(existingReservation);
            return convertReservationDto(reservation);
        } else {
            throw new ResourceNotFoundException("Guest", "id", id);
        }

    }

    public void deleteReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Reservation", "id", id));
        reservationRepository.delete(reservation);
    }

    private ReservationDto convertReservationDto(Reservation reservation) {
        return ReservationDto.builder()
                .id(reservation.getId())
                .startDate(reservation.getStartDate())
                .endDate(reservation.getEndDate())
                .room(reservation.getRoom().getId())
                .guests(reservation.getGuests().stream()
                        .map(Guest::getFirstName)
                        .collect(Collectors.toList()))
                .build();
    }

    public ReservationDto updateReservationRoom(Long id, Long roomId) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        Optional<Room> optionalRoom = roomRepository.findById(roomId);


        if (optionalReservation.isPresent() && optionalRoom.isPresent()) {
            Reservation existingReservation = optionalReservation.get();
            existingReservation.setRoom(optionalRoom.get());
            Reservation reservation = reservationRepository.save(existingReservation);
            return convertReservationDto(reservation);
        } else {
            throw new ResourceNotFoundException("Guest", "id", id);
        }

    }

}