package com.hotel.dto;

import lombok.Data;
import lombok.Builder;

import java.util.List;

@Data
@Builder
public class RoomDto {
    private Long id;
    private int price;
    private String roomNumber;
    private String roomType;
    private String occupancy;
    private int numberOfBeds;
    private List<Long> reservations;
}