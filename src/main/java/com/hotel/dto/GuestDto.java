package com.hotel.dto;

import lombok.Data;
import lombok.Builder;

import java.util.List;

@Data
@Builder
public class GuestDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String passportNumber;
    private List<Long> reservation;
}