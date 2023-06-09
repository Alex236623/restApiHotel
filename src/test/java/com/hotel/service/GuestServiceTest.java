package com.hotel.service;
import com.hotel.dto.GuestDto;
import com.hotel.domain.Guest;
import com.hotel.repository.GuestRepository;
import com.hotel.exception.ResourceNotFoundException;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        // Mock the repository response
        List<Guest> guests = new ArrayList<>();
        guests.add(new Guest(1L, "John", "Doe", "1234567890", new ArrayList<>()));
        guests.add(new Guest(2L, "Jane", "Smith", "0987654321", new ArrayList<>()));
        when(guestRepository.findAll()).thenReturn(guests);

        // Invoke the service method
        List<GuestDto> listGuestDto = guestService.findAll();

        // Verify the results
        assertEquals(2, listGuestDto.size());
        assertEquals("John", listGuestDto.get(0).getFirstName());
        assertEquals("Doe", listGuestDto.get(0).getLastName());
        assertEquals("Jane", listGuestDto.get(1).getFirstName());
        assertEquals("Smith", listGuestDto.get(1).getLastName());

        // Verify that the repository method was called
        verify(guestRepository, times(1)).findAll();
    }

    @Test
    void testGetGuestById() {
        // Mock the repository response
        Guest guest = new Guest(1L, "John", "Doe", "1234567890", new ArrayList<>());
        when(guestRepository.findById(1L)).thenReturn(Optional.of(guest));

        // Invoke the service method
        GuestDto guestDto = guestService.getGuestById(1L);

        // Verify the result
        assertEquals("John", guestDto.getFirstName());
        assertEquals("Doe", guestDto.getLastName());

        // Verify that the repository method was called
        verify(guestRepository, times(1)).findById(1L);
    }

    @Test
    void testGetGuestById_ThrowsExceptionWhenGuestNotFound() {
        // Mock the repository response
        when(guestRepository.findById(1L)).thenReturn(Optional.empty());

        // Verify that the service method throws ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> guestService.getGuestById(1L));

        // Verify that the repository method was called
        verify(guestRepository, times(1)).findById(1L);
    }
    @Test
    void testGetGuestByFirstName() {
        // Mock the repository response
        Guest guest = new Guest(1L, "John", "Doe", "1234567890", new ArrayList<>());
        when(guestRepository.findByFirstName("John")).thenReturn(Optional.of(guest));

        // Invoke the service method
        GuestDto guestDto = guestService.getGuestByFirstName("John");

        // Verify the result
        assertEquals("John", guestDto.getFirstName());
        assertEquals("Doe", guestDto.getLastName());

        // Verify that the repository method was called
        verify(guestRepository, times(1)).findByFirstName("John");
    }

    @Test
    void testGetGuestByFirstName_ThrowsExceptionWhenGuestNotFound() {
        // Mock the repository response
        when(guestRepository.findByFirstName("John")).thenReturn(Optional.empty());

        // Verify that the service method throws ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> guestService.getGuestByFirstName("John"));

        // Verify that the repository method was called
        verify(guestRepository, times(1)).findByFirstName("John");
    }

    @Test
    void testGetGuestByPassport() {
        // Mock the repository response
        Guest guest = new Guest(1L, "John", "Doe", "1234567890", new ArrayList<>());
        when(guestRepository.findByPassportNumber("1234567890")).thenReturn(Optional.of(guest));

        // Invoke the service method
        GuestDto guestDto = guestService.getGuestByPassport("1234567890");

        // Verify the result
        assertEquals("John", guestDto.getFirstName());
        assertEquals("Doe", guestDto.getLastName());

        // Verify that the repository method was called
        verify(guestRepository, times(1)).findByPassportNumber("1234567890");
    }

    @Test
    void testGetGuestByPassport_ThrowsExceptionWhenGuestNotFound() {
        // Mock the repository response
        when(guestRepository.findByPassportNumber("1234567890")).thenReturn(Optional.empty());

        // Verify that the service method throws ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> guestService.getGuestByPassport("1234567890"));

        // Verify that the repository method was called
        verify(guestRepository, times(1)).findByPassportNumber("1234567890");
    }

    @Test
    void testSaveGuest() {
        // Create a guest
        Guest guest = new Guest(1L, "John", "Doe", "1234567890", new ArrayList<>());

        // Mock the repository response
        when(guestRepository.save(guest)).thenReturn(guest);

        // Invoke the service method
        Guest savedGuest = guestService.saveGuest(guest);

        // Verify the result
        assertEquals(guest, savedGuest);

        // Verify that the repository method was called
        verify(guestRepository, times(1)).save(guest);
    }

    @Test
    void testUpdateGuest() {
        // Mock the repository response
        Guest existingGuest = new Guest(1L, "John", "Doe", "1234567890", new ArrayList<>());
        GuestDto updatedGuestDto = GuestDto.builder()
                .firstName("Jane")
                .lastName("Smith")
                .passportNumber("0987654321")
                .build();
        when(guestRepository.findById(1L)).thenReturn(Optional.of(existingGuest));
        when(guestRepository.save(existingGuest)).thenReturn(existingGuest);

        // Invoke the service method
        GuestDto updatedGuest = guestService.updateGuest(1L, updatedGuestDto);

        // Verify the result
        assertEquals("Jane", updatedGuest.getFirstName());
        assertEquals("Smith", updatedGuest.getLastName());

        // Verify that the repository method was called
        verify(guestRepository, times(1)).findById(1L);
        verify(guestRepository, times(1)).save(existingGuest);
    }

    @Test
    void testUpdateGuest_ThrowsExceptionWhenGuestNotFound() {
        // Mock the repository response
        GuestDto updatedGuestDto = GuestDto.builder()
                .firstName("Jane")
                .lastName("Smith")
                .passportNumber("0987654321")
                .build();
        when(guestRepository.findById(1L)).thenReturn(Optional.empty());

        // Verify that the service method throws ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> guestService.updateGuest(1L, updatedGuestDto));

        // Verify that the repository method was called
        verify(guestRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteGuest() {
        // Mock the repository response
        Guest guest = new Guest(1L, "John", "Doe", "1234567890", new ArrayList<>());
        when(guestRepository.findById(1L)).thenReturn(Optional.of(guest));

        // Invoke the service method
        guestService.deleteGuest(1L);

        // Verify that the repository method was called
        verify(guestRepository, times(1)).findById(1L);
        verify(guestRepository, times(1)).delete(guest);
    }

    @Test
    void testDeleteGuest_ThrowsExceptionWhenGuestNotFound() {
        // Mock the repository response
        when(guestRepository.findById(1L)).thenReturn(Optional.empty());

        // Verify that the service method throws ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> guestService.deleteGuest(1L));

        // Verify that the repository method was called
        verify(guestRepository, times(1)).findById(1L);
    }

}