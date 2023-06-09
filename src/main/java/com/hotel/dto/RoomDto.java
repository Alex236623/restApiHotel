package com.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomDto {
    private Long id;
    private Long price;
    private String roomNumber;
    private String roomType;
    private String occupancy;
    private Long numberOfBeds;
    private List<Long> reservations;
}