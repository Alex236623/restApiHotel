package com.hotel.controller;

import com.hotel.domain.Reservation;
import com.hotel.dto.ReservationDto;
import com.hotel.service.ReservationService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ReservationControllerTest {

    @Mock
    private ReservationService reservationService;

    @InjectMocks
    private ReservationController reservationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllReservations() {
        List<ReservationDto> expectedReservations = new ArrayList<>();
        when(reservationService.getAllReservations()).thenReturn(expectedReservations);

        List<ReservationDto> actualReservations = reservationController.getAllReservations();

        assertNotNull(actualReservations);
        assertEquals(expectedReservations, actualReservations);
        verify(reservationService, times(1)).getAllReservations();
    }

    @Test
    void testGetReservationById() {
        Long reservationId = 1L;
        ReservationDto expectedReservation = new ReservationDto();
        when(reservationService.getReservationById(reservationId)).thenReturn(expectedReservation);

        ReservationDto actualReservation = reservationController.getReservationById(reservationId);

        assertNotNull(actualReservation);
        assertEquals(expectedReservation, actualReservation);
        verify(reservationService, times(1)).getReservationById(reservationId);
    }

    @Test
    void testAddReservation() {
        Reservation reservation = new Reservation();
        Reservation savedReservation = new Reservation();
        when(reservationService.addReservation(reservation)).thenReturn(savedReservation);

        ResponseEntity<Reservation> response = reservationController.addReservation(reservation);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedReservation, response.getBody());
        verify(reservationService, times(1)).addReservation(reservation);
    }

    @Test
    void testUpdateReservation() {
        Long reservationId = 1L;
        ReservationDto reservationDto = new ReservationDto();
        ReservationDto updatedReservationDto = new ReservationDto();
        when(reservationService.updateReservation(reservationId, reservationDto)).thenReturn(updatedReservationDto);

        ResponseEntity<ReservationDto> response = reservationController.updateReservation(reservationId, reservationDto);

        assertNotNull(response);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals(updatedReservationDto, response.getBody());
        verify(reservationService, times(1)).updateReservation(reservationId, reservationDto);
    }

    @Test
    void testUpdateReservationRoom() {
        Long reservationId = 1L;
        Long roomId = 2L;
        ReservationDto updatedReservationDto = new ReservationDto();
        when(reservationService.updateReservationRoom(reservationId, roomId)).thenReturn(updatedReservationDto);

        ResponseEntity<ReservationDto> response = reservationController.updateReservation(reservationId, roomId);

        assertNotNull(response);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals(updatedReservationDto, response.getBody());
        verify(reservationService, times(1)).updateReservationRoom(reservationId, roomId);
    }

    @Test
    void testDeleteReservation() {
        Long reservationId = 1L;

        reservationController.deleteReservation(reservationId);

        verify(reservationService, times(1)).deleteReservation(reservationId);
    }
}