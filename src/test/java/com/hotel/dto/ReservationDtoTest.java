package com.hotel.dto;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ReservationDtoTest {

    @Test
    void testReservationDtoCreation() {
        ReservationDto reservationDto = new ReservationDto();
        assertNotNull(reservationDto);
    }

    @Test
    void testReservationDtoFields() {
        Long id = 1L;
        LocalDate startDate = LocalDate.of(2023, 5, 1);
        LocalDate endDate = LocalDate.of(2023, 5, 5);
        Long room = 100L;
        List<Long> guests = new ArrayList<>();
        guests.add(1L);
        guests.add(2L);

        ReservationDto reservationDto = ReservationDto.builder()
                .id(id)
                .startDate(startDate)
                .endDate(endDate)
                .room(room)
                .guests(guests)
                .build();

        assertEquals(id, reservationDto.getId());
        assertEquals(startDate, reservationDto.getStartDate());
        assertEquals(endDate, reservationDto.getEndDate());
        assertEquals(room, reservationDto.getRoom());
        assertEquals(guests, reservationDto.getGuests());
    }
}