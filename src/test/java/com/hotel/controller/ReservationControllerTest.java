package com.hotel.controller;
import com.hotel.domain.Reservation;
import com.hotel.dto.ReservationDto;
import com.hotel.service.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ReservationControllerTest {

    @Mock
    private ReservationService reservationService;

    @InjectMocks
    private ReservationController reservationController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void testGetAllReservations() {
        // Arrange
        List<ReservationDto> expectedReservations = new ArrayList<>();
        when(reservationService.getAllReservations()).thenReturn(expectedReservations);

        // Act
        List<ReservationDto> actualReservations = reservationController.getAllReservations();

        // Assert
        assertNotNull(actualReservations);
        assertEquals(expectedReservations, actualReservations);
        verify(reservationService, times(1)).getAllReservations();
    }

    @Test
    public void testGetReservationById() {
        // Arrange
        Long reservationId = 1L;
        ReservationDto expectedReservation = new ReservationDto();
        when(reservationService.getReservationById(reservationId)).thenReturn(expectedReservation);

        // Act
        ReservationDto actualReservation = reservationController.getReservationById(reservationId);

        // Assert
        assertNotNull(actualReservation);
        assertEquals(expectedReservation, actualReservation);
        verify(reservationService, times(1)).getReservationById(reservationId);
    }

    @Test
    public void testAddReservation() {
        // Arrange
        Reservation reservation = new Reservation();
        Reservation savedReservation = new Reservation();
        when(reservationService.addReservation(reservation)).thenReturn(savedReservation);

        // Act
        ResponseEntity<Reservation> response = reservationController.addReservation(reservation);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedReservation, response.getBody());
        verify(reservationService, times(1)).addReservation(reservation);
    }

    @Test
    public void testUpdateReservation() {
        // Arrange
        Long reservationId = 1L;
        ReservationDto reservationDto = new ReservationDto();
        ReservationDto updatedReservationDto = new ReservationDto();
        when(reservationService.updateReservation(reservationId, reservationDto)).thenReturn(updatedReservationDto);

        // Act
        ResponseEntity<ReservationDto> response = reservationController.updateReservation(reservationId, reservationDto);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals(updatedReservationDto, response.getBody());
        verify(reservationService, times(1)).updateReservation(reservationId, reservationDto);
    }

    @Test
    public void testUpdateReservationRoom() {
        // Arrange
        Long reservationId = 1L;
        Long roomId = 2L;
        ReservationDto updatedReservationDto = new ReservationDto();
        when(reservationService.updateReservationRoom(reservationId, roomId)).thenReturn(updatedReservationDto);

        // Act
        ResponseEntity<ReservationDto> response = reservationController.updateReservation(reservationId, roomId);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals(updatedReservationDto, response.getBody());
        verify(reservationService, times(1)).updateReservationRoom(reservationId, roomId);
    }

    @Test
    public void testDeleteReservation() {
        // Arrange
        Long reservationId = 1L;

        // Act
        reservationController.deleteReservation(reservationId);

        // Assert
        verify(reservationService, times(1)).deleteReservation(reservationId);
    }
}