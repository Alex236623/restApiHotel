package com.hotel.service;

import com.hotel.domain.Room;
import com.hotel.domain.Reservation;
import com.hotel.dto.ReservationDto;
import com.hotel.repository.RoomRepository;
import com.hotel.repository.ReservationRepository;
import com.hotel.exception.ResourceNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReservationServiceTest {

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
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(new Reservation(1L, LocalDate.now(), LocalDate.now().plusDays(3), new Room(), new ArrayList<>()));
        reservations.add(new Reservation(2L, LocalDate.now().plusDays(5), LocalDate.now().plusDays(7), new Room(), new ArrayList<>()));
        when(reservationRepository.findAll()).thenReturn(reservations);

        List<ReservationDto> reservationDtos = reservationService.getAllReservations();

        assertEquals(2, reservationDtos.size());
        assertEquals(1L, reservationDtos.get(0).getId());
        assertEquals(2L, reservationDtos.get(1).getId());

        verify(reservationRepository, times(1)).findAll();
    }

    @Test
    void testGetReservationById() {
        Reservation reservation = new Reservation(1L, LocalDate.now(), LocalDate.now().plusDays(3), new Room(), new ArrayList<>());
        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));

        ReservationDto reservationDto = reservationService.getReservationById(1L);

        assertEquals(1L, reservationDto.getId());

        verify(reservationRepository, times(1)).findById(1L);
    }

    @Test
    void testGetReservationById_ThrowsExceptionWhenReservationNotFound() {
        when(reservationRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> reservationService.getReservationById(1L));

        verify(reservationRepository, times(1)).findById(1L);
    }

    @Test
    void testAddReservation() {
        Reservation reservation = new Reservation(1L, LocalDate.now(), LocalDate.now().plusDays(3), new Room(), new ArrayList<>());

        when(reservationRepository.existsByRoomAndStartDateBetweenOrRoomAndEndDateBetween(reservation.getRoom(), reservation.getStartDate(), reservation.getEndDate(), reservation.getRoom(), reservation.getStartDate(), reservation.getEndDate())).thenReturn(false);
        Optional<Room> room = Optional.of(new Room());
        when(roomRepository.findById(reservation.getRoom().getId())).thenReturn(room);
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        Reservation addedReservation = reservationService.addReservation(reservation);

        assertEquals(reservation, addedReservation);

        verify(reservationRepository, times(1)).existsByRoomAndStartDateBetweenOrRoomAndEndDateBetween(reservation.getRoom(), reservation.getStartDate(), reservation.getEndDate(), reservation.getRoom(), reservation.getStartDate(), reservation.getEndDate());
        verify(roomRepository, times(1)).findById(reservation.getRoom().getId());
        verify(reservationRepository, times(1)).save(reservation);
    }

    @Test
    void testAddReservation_ThrowsExceptionWhenRoomOccupied() {
        Reservation reservation = new Reservation(1L, LocalDate.now(), LocalDate.now().plusDays(3), new Room(), new ArrayList<>());

        when(reservationRepository.existsByRoomAndStartDateBetweenOrRoomAndEndDateBetween(reservation.getRoom(), reservation.getStartDate(), reservation.getEndDate(), reservation.getRoom(), reservation.getStartDate(), reservation.getEndDate())).thenReturn(true);

        assertThrows(RuntimeException.class, () -> reservationService.addReservation(reservation));

        verify(reservationRepository, times(1)).existsByRoomAndStartDateBetweenOrRoomAndEndDateBetween(reservation.getRoom(), reservation.getStartDate(), reservation.getEndDate(), reservation.getRoom(), reservation.getStartDate(), reservation.getEndDate());
    }

    @Test
    void testUpdateReservation() {
        Reservation reservation = new Reservation(1L, LocalDate.now(), LocalDate.now().plusDays(3), new Room(), new ArrayList<>());
        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        ReservationDto updatedReservationDto = new ReservationDto(1L, LocalDate.now().plusDays(1), LocalDate.now().plusDays(4), 1L, new ArrayList<>());
        ReservationDto updatedReservation = reservationService.updateReservation(1L, updatedReservationDto);

        assertEquals(updatedReservationDto.getId(), updatedReservation.getId());
        assertEquals(updatedReservationDto.getStartDate(), updatedReservation.getStartDate());
        assertEquals(updatedReservationDto.getEndDate(), updatedReservation.getEndDate());

        verify(reservationRepository, times(1)).findById(1L);
        verify(reservationRepository, times(1)).save(reservation);
    }

    @Test
    void testUpdateReservation_ThrowsExceptionWhenReservationNotFound() {
        ReservationDto updatedReservationDto = new ReservationDto(1L, LocalDate.now().plusDays(1), LocalDate.now().plusDays(4), 1L, new ArrayList<>());
        when(reservationRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> reservationService.updateReservation(1L, updatedReservationDto));

        verify(reservationRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteReservation() {
        Reservation reservation = new Reservation(1L, LocalDate.now(), LocalDate.now().plusDays(3), new Room(), new ArrayList<>());
        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));

        reservationService.deleteReservation(1L);

        verify(reservationRepository, times(1)).findById(1L);
        verify(reservationRepository, times(1)).delete(reservation);
    }

    @Test
    void testDeleteReservation_ThrowsExceptionWhenReservationNotFound() {
        when(reservationRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> reservationService.deleteReservation(1L));

        verify(reservationRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateReservationRoom() {
        Room room = new Room(1L, (long) 100.0, "101", "Standard", "2", 2L, new ArrayList<>());
        Reservation reservation = new Reservation(1L, LocalDate.now(), LocalDate.now().plusDays(3), room, new ArrayList<>());
        Optional<Reservation> optionalReservation = Optional.of(reservation);

        Optional<Room> optionalRoom = Optional.of(new Room());
        when(reservationRepository.findById(1L)).thenReturn(optionalReservation);
        when(roomRepository.findById(1L)).thenReturn(optionalRoom);
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        ReservationDto updatedReservationDto = reservationService.updateReservationRoom(1L, 1L);

        assertEquals(1L, updatedReservationDto.getId());

        verify(reservationRepository, times(1)).findById(1L);
        verify(roomRepository, times(1)).findById(1L);
        verify(reservationRepository, times(1)).save(reservation);
    }

    @Test
    void testUpdateReservationRoom_ThrowsExceptionWhenReservationOrRoomNotFound() {
        when(reservationRepository.findById(1L)).thenReturn(Optional.empty());
        when(roomRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> reservationService.updateReservationRoom(1L, 1L));

        verify(reservationRepository, times(1)).findById(1L);
        verify(roomRepository, times(1)).findById(1L);
    }
}