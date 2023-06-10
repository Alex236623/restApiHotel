package com.hotel.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GuestTest {

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGuestCreation() {
        Guest guest = new Guest();
        assertNotNull(guest);
    }

    @Test
    void testGuestFields() {
        String firstName = "John";
        String lastName = "Doe";
        String passportNumber = "ABC123";
        List<Reservation> reservations = new ArrayList<>();

        Guest guest = new Guest();
        guest.setFirstName(firstName);
        guest.setLastName(lastName);
        guest.setPassportNumber(passportNumber);
        guest.setReservation(reservations);

        assertEquals(firstName, guest.getFirstName());
        assertEquals(lastName, guest.getLastName());
        assertEquals(passportNumber, guest.getPassportNumber());
        assertEquals(reservations, guest.getReservation());
    }
}