package com.hotel.dto;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RoomDtoTest {

    @Test
    void testRoomDtoCreation() {
        RoomDto roomDto = new RoomDto();
        assertNotNull(roomDto);
    }

    @Test
    void testRoomDtoFields() {
        Long id = 1L;
        Long price = 100L;
        String roomNumber = "101";
        String roomType = "Deluxe";
        String occupancy = "Single";
        Long numberOfBeds = 1L;
        List<Long> reservations = new ArrayList<>();
        reservations.add(1L);
        reservations.add(2L);

        RoomDto roomDto = RoomDto.builder()
                .id(id)
                .price(price)
                .roomNumber(roomNumber)
                .roomType(roomType)
                .occupancy(occupancy)
                .numberOfBeds(numberOfBeds)
                .reservations(reservations)
                .build();

        assertEquals(id, roomDto.getId());
        assertEquals(price, roomDto.getPrice());
        assertEquals(roomNumber, roomDto.getRoomNumber());
        assertEquals(roomType, roomDto.getRoomType());
        assertEquals(occupancy, roomDto.getOccupancy());
        assertEquals(numberOfBeds, roomDto.getNumberOfBeds());
        assertEquals(reservations, roomDto.getReservations());
    }
}