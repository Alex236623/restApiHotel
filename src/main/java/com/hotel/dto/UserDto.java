package com.hotel.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private Long id;
    private String login;
    private String password;
    private Long guest;
}
