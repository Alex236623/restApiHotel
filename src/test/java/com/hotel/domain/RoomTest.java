package com.hotel.domain;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RoomTest {

    @Test
    void testRoomCreation() {
        Room room = new Room();
        assertNotNull(room);
    }

    @Test
    void testRoomFields() {
        Long price = 100L;
        String roomNumber = "101";
        String roomType = "Standard";
        String occupancy = "Single";
        Long numberOfBeds = 1L;
        List<Reservation> reservations = new ArrayList<>();

        Room room = new Room();
        room.setPrice(price);
        room.setRoomNumber(roomNumber);
        room.setRoomType(roomType);
        room.setOccupancy(occupancy);
        room.setNumberOfBeds(numberOfBeds);
        room.setReservations(reservations);

        assertEquals(price, room.getPrice());
        assertEquals(roomNumber, room.getRoomNumber());
        assertEquals(roomType, room.getRoomType());
        assertEquals(occupancy, room.getOccupancy());
        assertEquals(numberOfBeds, room.getNumberOfBeds());
        assertEquals(reservations, room.getReservations());
    }

    @Test
    void testRoomReservations() {
        Room room = new Room();
        Reservation reservation1 = new Reservation();
        Reservation reservation2 = new Reservation();

        room.getReservations().add(reservation1);
        room.getReservations().add(reservation2);

        assertEquals(2, room.getReservations().size());
        assertEquals(reservation1, room.getReservations().get(0));
        assertEquals(reservation2, room.getReservations().get(1));
    }
}
