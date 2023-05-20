package com.hotel.dto;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class GuestDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String passportNumber;
    private Long reservation;
}