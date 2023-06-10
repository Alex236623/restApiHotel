package com.hotel.domain;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ReservationTest {

    @Test
    void testReservationCreation() {
        Reservation reservation = new Reservation();
        assertNotNull(reservation);
    }

    @Test
    void testReservationFields() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusDays(7);
        Room room = new Room();
        List<Guest> guests = new ArrayList<>();

        Reservation reservation = new Reservation();
        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);
        reservation.setRoom(room);
        reservation.setGuests(guests);

        assertEquals(startDate, reservation.getStartDate());
        assertEquals(endDate, reservation.getEndDate());
        assertEquals(room, reservation.getRoom());
        assertEquals(guests, reservation.getGuests());
    }

    @Test
    void testReservationGuests() {
        Reservation reservation = new Reservation();
        Guest guest1 = new Guest();
        Guest guest2 = new Guest();

        reservation.getGuests().add(guest1);
        reservation.getGuests().add(guest2);

        assertEquals(2, reservation.getGuests().size());
        assertEquals(guest1, reservation.getGuests().get(0));
        assertEquals(guest2, reservation.getGuests().get(1));
    }
}