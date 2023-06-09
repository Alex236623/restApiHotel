package com.hotel.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserDtoTest {

    @Test
    void testUserDtoCreation() {
        UserDto userDto = new UserDto();
        assertNotNull(userDto);
    }

    @Test
    void testUserDtoFields() {
        Long id = 1L;
        String login = "john";
        String password = "password";
        Long guest = 1L;

        UserDto userDto = UserDto.builder()
                .id(id)
                .login(login)
                .password(password)
                .guest(guest)
                .build();

        assertEquals(id, userDto.getId());
        assertEquals(login, userDto.getLogin());
        assertEquals(password, userDto.getPassword());
        assertEquals(guest, userDto.getGuest());
    }
}
