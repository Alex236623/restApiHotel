package com.hotel.controller;

import com.hotel.domain.Reservation;
import com.hotel.dto.ReservationDto;
import com.hotel.service.ReservationService;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public List<ReservationDto> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    public ReservationDto getReservationById(@PathVariable Long id) {
        return reservationService.getReservationById(id);
    }

    @PostMapping
    public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation) {
        return new ResponseEntity<>(reservationService.addReservation(reservation), HttpStatusCode.valueOf(201));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationDto> updateReservation(@PathVariable Long id, @RequestBody ReservationDto reservation) {
        return new ResponseEntity<>(reservationService.updateReservation(id, reservation), HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}/{roomId}")
    public ResponseEntity<ReservationDto> updateReservation(@PathVariable Long reservationId, @PathVariable Long roomId) {
        return new ResponseEntity<>(reservationService.updateReservationRoom(reservationId, roomId), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
    }
}