package com.hotel.controller;

import com.hotel.domain.Reservation;
import com.hotel.dto.ReservationDto;
import com.hotel.service.ReservationService;

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
    public ReservationDto updateReservation(@PathVariable Long id, @RequestBody ReservationDto reservation) {
        return reservationService.updateReservation(id, reservation);
    }

    @PutMapping("/{id}/{roomId}")
    public ReservationDto updateReservation(@PathVariable Long id, @PathVariable Long roomId) {
        return reservationService.updateReservationRoom(id, roomId);
    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
    }
}