package com.hotel.service;

import com.hotel.domain.Room;
import com.hotel.domain.Reservation;
import com.hotel.dto.ReservationDto;
import com.hotel.repository.RoomRepository;
import com.hotel.repository.ReservationRepository;
import com.hotel.exception.ResourceNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private ReservationService reservationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllReservations() {
        // Mock the repository response
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(new Reservation(1L, LocalDate.now(), LocalDate.now().plusDays(3), new Room(), new ArrayList<>()));
        reservations.add(new Reservation(2L, LocalDate.now().plusDays(5), LocalDate.now().plusDays(7), new Room(), new ArrayList<>()));
        when(reservationRepository.findAll()).thenReturn(reservations);

        // Invoke the service method
        List<ReservationDto> reservationDtos = reservationService.getAllReservations();

        // Verify the results
        assertEquals(2, reservationDtos.size());
        assertEquals(1L, reservationDtos.get(0).getId());
        assertEquals(2L, reservationDtos.get(1).getId());

        // Verify that the repository method was called
        verify(reservationRepository, times(1)).findAll();
    }

    @Test
    void testGetReservationById() {
        // Mock the repository response
        Reservation reservation = new Reservation(1L, LocalDate.now(), LocalDate.now().plusDays(3), new Room(), new ArrayList<>());
        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));

        // Invoke the service method
        ReservationDto reservationDto = reservationService.getReservationById(1L);

        // Verify the result
        assertEquals(1L, reservationDto.getId());

        // Verify that the repository method was called
        verify(reservationRepository, times(1)).findById(1L);
    }

    @Test
    void testGetReservationById_ThrowsExceptionWhenReservationNotFound() {
        // Mock the repository response
        when(reservationRepository.findById(1L)).thenReturn(Optional.empty());

        // Verify that the service method throws ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> reservationService.getReservationById(1L));

        // Verify that the repository method was called
        verify(reservationRepository, times(1)).findById(1L);
    }

    @Test
    void testAddReservation() {
        // Create a reservation
        Reservation reservation = new Reservation(1L, LocalDate.now(), LocalDate.now().plusDays(3), new Room(), new ArrayList<>());

        // Mock the repository response
        when(reservationRepository.existsByRoomAndStartDateBetweenOrRoomAndEndDateBetween(reservation.getRoom(), reservation.getStartDate(), reservation.getEndDate(), reservation.getRoom(), reservation.getStartDate(), reservation.getEndDate())).thenReturn(false);
        Optional<Room> room = Optional.of(new Room());
        when(roomRepository.findById(reservation.getRoom().getId())).thenReturn(room);
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        // Invoke the service method
        Reservation addedReservation = reservationService.addReservation(reservation);

        // Verify the result
        assertEquals(reservation, addedReservation);

        // Verify that the repository methods were called
        verify(reservationRepository, times(1)).existsByRoomAndStartDateBetweenOrRoomAndEndDateBetween(reservation.getRoom(), reservation.getStartDate(), reservation.getEndDate(), reservation.getRoom(), reservation.getStartDate(), reservation.getEndDate());
        verify(roomRepository, times(1)).findById(reservation.getRoom().getId());
        verify(reservationRepository, times(1)).save(reservation);
    }

    @Test
    void testAddReservation_ThrowsExceptionWhenRoomOccupied() {
        // Create a reservation
        Reservation reservation = new Reservation(1L, LocalDate.now(), LocalDate.now().plusDays(3), new Room(), new ArrayList<>());

        // Mock the repository response
        when(reservationRepository.existsByRoomAndStartDateBetweenOrRoomAndEndDateBetween(reservation.getRoom(), reservation.getStartDate(), reservation.getEndDate(), reservation.getRoom(), reservation.getStartDate(), reservation.getEndDate())).thenReturn(true);

        // Verify that the service method throws RuntimeException
        assertThrows(RuntimeException.class, () -> reservationService.addReservation(reservation));

        // Verify that the repository method was called
        verify(reservationRepository, times(1)).existsByRoomAndStartDateBetweenOrRoomAndEndDateBetween(reservation.getRoom(), reservation.getStartDate(), reservation.getEndDate(), reservation.getRoom(), reservation.getStartDate(), reservation.getEndDate());
    }

    @Test
    void testUpdateReservation() {
        // Mock the repository response
        Reservation reservation = new Reservation(1L, LocalDate.now(), LocalDate.now().plusDays(3), new Room(), new ArrayList<>());
        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        // Invoke the service method
        ReservationDto updatedReservationDto = new ReservationDto(1L, LocalDate.now().plusDays(1), LocalDate.now().plusDays(4), 1L, new ArrayList<>());
        ReservationDto updatedReservation = reservationService.updateReservation(1L, updatedReservationDto);

        // Verify the result
        assertEquals(updatedReservationDto.getId(), updatedReservation.getId());
        assertEquals(updatedReservationDto.getStartDate(), updatedReservation.getStartDate());
        assertEquals(updatedReservationDto.getEndDate(), updatedReservation.getEndDate());

        // Verify that the repository methods were called
        verify(reservationRepository, times(1)).findById(1L);
        verify(reservationRepository, times(1)).save(reservation);
    }

    @Test
    void testUpdateReservation_ThrowsExceptionWhenReservationNotFound() {
        // Mock the repository response
        ReservationDto updatedReservationDto = new ReservationDto(1L, LocalDate.now().plusDays(1), LocalDate.now().plusDays(4), 1L, new ArrayList<>());
        when(reservationRepository.findById(1L)).thenReturn(Optional.empty());

        // Verify that the service method throws ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> reservationService.updateReservation(1L, updatedReservationDto));

        // Verify that the repository method was called
        verify(reservationRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteReservation() {
        // Mock the repository response
        Reservation reservation = new Reservation(1L, LocalDate.now(), LocalDate.now().plusDays(3), new Room(), new ArrayList<>());
        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));

        // Invoke the service method
        reservationService.deleteReservation(1L);

        // Verify that the repository method was called
        verify(reservationRepository, times(1)).findById(1L);
        verify(reservationRepository, times(1)).delete(reservation);
    }

    @Test
    void testDeleteReservation_ThrowsExceptionWhenReservationNotFound() {
        // Mock the repository response
        when(reservationRepository.findById(1L)).thenReturn(Optional.empty());

        // Verify that the service method throws ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> reservationService.deleteReservation(1L));

        // Verify that the repository method was called
        verify(reservationRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateReservationRoom() {

        Room room = new Room(1L, (long) 100.0, "101", "Standard", "2", 2L, new ArrayList<>());
        // Mock the repository response
        Reservation reservation = new Reservation(1L, LocalDate.now(), LocalDate.now().plusDays(3), room, new ArrayList<>());
        Optional<Reservation> optionalReservation = Optional.of(reservation);

        Optional<Room> optionalRoom = Optional.of(new Room());
        when(reservationRepository.findById(1L)).thenReturn(optionalReservation);
        when(roomRepository.findById(1L)).thenReturn(optionalRoom);
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        // Invoke the service method
        ReservationDto updatedReservationDto = reservationService.updateReservationRoom(1L, 1L);

        // Verify the result
        assertEquals(1L, updatedReservationDto.getId());

        // Verify that the repository methods were called
        verify(reservationRepository, times(1)).findById(1L);
        verify(roomRepository, times(1)).findById(1L);
        verify(reservationRepository, times(1)).save(reservation);
    }

    @Test
    void testUpdateReservationRoom_ThrowsExceptionWhenReservationOrRoomNotFound() {
        // Mock the repository response
        when(reservationRepository.findById(1L)).thenReturn(Optional.empty());
        when(roomRepository.findById(1L)).thenReturn(Optional.empty());

        // Verify that the service method throws ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> reservationService.updateReservationRoom(1L, 1L));

        // Verify that the repository methods were called
        verify(reservationRepository, times(1)).findById(1L);
        verify(roomRepository, times(1)).findById(1L);
    }
}