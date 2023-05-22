/*
package com.hotel.service;

import com.hotel.domain.Guest;
import com.hotel.dto.GuestDto;
import com.hotel.exception.ResourceNotFoundException;
import com.hotel.repository.GuestRepository;
import com.hotel.service.GuestService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;

public class GuestServiceTest {
    @Mock
    private GuestRepository guestRepository;

    @InjectMocks
    private GuestService guestService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAllGuests() {
        // Prepare
        List<Guest> guests = new ArrayList<>();
        guests.add(new Guest(1L, "John", "Doe", "12345", null));
        guests.add(new Guest(2L, "Jane", "Smith", "54321", null));
        when(guestRepository.findAll()).thenReturn(guests);

        // Act
        List<GuestDto> guestDtos = guestService.findAll() .stream()
                .map(guest -> GuestDto.builder()
                        .id(guest.getId())
                        .firstName(guest.getFirstName())
                        .lastName(guest.getLastName())
                        .passportNumber(guest.getPassportNumber())
                        .reservation(guest.getReservation())
                        .build())
                .toList();


        // Assert
        Assertions.assertEquals(2, guestDtos.size());
        Assertions.assertEquals("John", guestDtos.get(0).getFirstName());
        Assertions.assertEquals("Doe", guestDtos.get(0).getLastName());
        Assertions.assertEquals("12345", guestDtos.get(0).getPassportNumber());
        Assertions.assertEquals("Jane", guestDtos.get(1).getFirstName());
        Assertions.assertEquals("Smith", guestDtos.get(1).getLastName());
        Assertions.assertEquals("54321", guestDtos.get(1).getPassportNumber());
        verify(guestRepository, times(1)).findAll();
    }

    @Test
    public void testGetGuestById_ExistingId_ReturnsGuestDto() {
        // Prepare
        Long guestId = 1L;
        Guest guest = new Guest(guestId, "John", "Doe", "12345", null);
        when(guestRepository.findById(guestId)).thenReturn(Optional.of(guest));

        // Act
        GuestDto guestDto = guestService.getGuestById(guestId);

        // Assert
        Assertions.assertEquals("John", guestDto.getFirstName());
        Assertions.assertEquals("Doe", guestDto.getLastName());
        Assertions.assertEquals("12345", guestDto.getPassportNumber());
        verify(guestRepository, times(1)).findById(guestId);
    }

    @Test
    public void testGetGuestById_NonExistingId_ThrowsResourceNotFoundException() {
        // Prepare
        Long guestId = 1L;
        when(guestRepository.findById(guestId)).thenReturn(Optional.empty());

        // Assert
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            guestService.getGuestById(guestId);
        });
        verify(guestRepository, times(1)).findById(guestId);
    }


}*/
