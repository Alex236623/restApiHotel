package com.hotel.service;

import com.hotel.domain.Guest;
import com.hotel.dto.GuestDto;
import com.hotel.repository.GuestRepository;
import com.hotel.exception.ResourceNotFoundException;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class GuestServiceTest {

    @Mock
    private GuestRepository guestRepository;

    private GuestService guestService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        guestService = new GuestService(guestRepository);
    }

    @Test
    void testFindAll() {
        List<Guest> guests = new ArrayList<>();
        guests.add(new Guest(1L, "John", "Doe", "1234567890", new ArrayList<>()));
        guests.add(new Guest(2L, "Jane", "Smith", "0987654321", new ArrayList<>()));
        when(guestRepository.findAll()).thenReturn(guests);

        List<GuestDto> listGuestDto = guestService.findAll();

        assertEquals(2, listGuestDto.size());
        assertEquals("John", listGuestDto.get(0).getFirstName());
        assertEquals("Doe", listGuestDto.get(0).getLastName());
        assertEquals("Jane", listGuestDto.get(1).getFirstName());
        assertEquals("Smith", listGuestDto.get(1).getLastName());

        verify(guestRepository, times(1)).findAll();
    }

    @Test
    void testGetGuestById() {
        Guest guest = new Guest(1L, "John", "Doe", "1234567890", new ArrayList<>());
        when(guestRepository.findById(1L)).thenReturn(Optional.of(guest));

        GuestDto guestDto = guestService.getGuestById(1L);

        assertEquals("John", guestDto.getFirstName());
        assertEquals("Doe", guestDto.getLastName());

        verify(guestRepository, times(1)).findById(1L);
    }

    @Test
    void testGetGuestById_ThrowsExceptionWhenGuestNotFound() {
        when(guestRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> guestService.getGuestById(1L));

        verify(guestRepository, times(1)).findById(1L);
    }

    @Test
    void testGetGuestByFirstName() {
        Guest guest = new Guest(1L, "John", "Doe", "1234567890", new ArrayList<>());
        when(guestRepository.findByFirstName("John")).thenReturn(Optional.of(guest));

        GuestDto guestDto = guestService.getGuestByFirstName("John");

        assertEquals("John", guestDto.getFirstName());
        assertEquals("Doe", guestDto.getLastName());

        verify(guestRepository, times(1)).findByFirstName("John");
    }

    @Test
    void testGetGuestByFirstName_ThrowsExceptionWhenGuestNotFound() {
        when(guestRepository.findByFirstName("John")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> guestService.getGuestByFirstName("John"));

        verify(guestRepository, times(1)).findByFirstName("John");
    }

    @Test
    void testGetGuestByPassport() {
        Guest guest = new Guest(1L, "John", "Doe", "1234567890", new ArrayList<>());
        when(guestRepository.findByPassportNumber("1234567890")).thenReturn(Optional.of(guest));

        GuestDto guestDto = guestService.getGuestByPassport("1234567890");

        assertEquals("John", guestDto.getFirstName());
        assertEquals("Doe", guestDto.getLastName());

        verify(guestRepository, times(1)).findByPassportNumber("1234567890");
    }

    @Test
    void testGetGuestByPassport_ThrowsExceptionWhenGuestNotFound() {
        when(guestRepository.findByPassportNumber("1234567890")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> guestService.getGuestByPassport("1234567890"));

        verify(guestRepository, times(1)).findByPassportNumber("1234567890");
    }

    @Test
    void testSaveGuest() {
        Guest guest = new Guest(1L, "John", "Doe", "1234567890", new ArrayList<>());

        when(guestRepository.save(guest)).thenReturn(guest);

        Guest savedGuest = guestService.saveGuest(guest);

        assertEquals(guest, savedGuest);

        verify(guestRepository, times(1)).save(guest);
    }

    @Test
    void testUpdateGuest() {
        Guest existingGuest = new Guest(1L, "John", "Doe", "1234567890", new ArrayList<>());
        GuestDto updatedGuestDto = GuestDto.builder()
                .firstName("Jane")
                .lastName("Smith")
                .passportNumber("0987654321")
                .build();
        when(guestRepository.findById(1L)).thenReturn(Optional.of(existingGuest));
        when(guestRepository.save(existingGuest)).thenReturn(existingGuest);

        GuestDto updatedGuest = guestService.updateGuest(1L, updatedGuestDto);

        assertEquals("Jane", updatedGuest.getFirstName());
        assertEquals("Smith", updatedGuest.getLastName());

        verify(guestRepository, times(1)).findById(1L);
        verify(guestRepository, times(1)).save(existingGuest);
    }

    @Test
    void testUpdateGuest_ThrowsExceptionWhenGuestNotFound() {
        GuestDto updatedGuestDto = GuestDto.builder()
                .firstName("Jane")
                .lastName("Smith")
                .passportNumber("0987654321")
                .build();
        when(guestRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> guestService.updateGuest(1L, updatedGuestDto));

        verify(guestRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteGuest() {
        Guest guest = new Guest(1L, "John", "Doe", "1234567890", new ArrayList<>());
        when(guestRepository.findById(1L)).thenReturn(Optional.of(guest));

        guestService.deleteGuest(1L);

        verify(guestRepository, times(1)).findById(1L);
        verify(guestRepository, times(1)).delete(guest);
    }

    @Test
    void testDeleteGuest_ThrowsExceptionWhenGuestNotFound() {
        when(guestRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> guestService.deleteGuest(1L));

        verify(guestRepository, times(1)).findById(1L);
    }
}