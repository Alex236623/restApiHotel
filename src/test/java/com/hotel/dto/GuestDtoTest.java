package com.hotel.dto;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GuestDtoTest {

    @Test
    void testGuestDtoCreation() {
        GuestDto guestDto = new GuestDto();
        assertNotNull(guestDto);
    }

    @Test
    void testGuestDtoFields() {
        Long id = 1L;
        String firstName = "John";
        String lastName = "Doe";
        String passportNumber = "AB123456";
        List<Long> reservations = new ArrayList<>();
        reservations.add(100L);
        reservations.add(200L);

        GuestDto guestDto = new GuestDto();
        guestDto.setId(id);
        guestDto.setFirstName(firstName);
        guestDto.setLastName(lastName);
        guestDto.setPassportNumber(passportNumber);
        guestDto.setReservation(reservations);

        assertEquals(id, guestDto.getId());
        assertEquals(firstName, guestDto.getFirstName());
        assertEquals(lastName, guestDto.getLastName());
        assertEquals(passportNumber, guestDto.getPassportNumber());
        assertEquals(reservations, guestDto.getReservation());
    }
}