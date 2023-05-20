package com.hotel.dto;

import lombok.Data;
import lombok.Builder;

import java.util.List;
import java.time.LocalDate;

@Builder
@Data
public class ReservationDto {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String room;
    private List<String> guests;
}