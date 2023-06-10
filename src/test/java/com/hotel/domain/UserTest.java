package com.hotel.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserTest {

    @Test
    void testUserCreation() {
        User user = new User();
        assertNotNull(user);
    }

    @Test
    void testUserFields() {
        String login = "Alex";
        String password = "Homes";
        Guest guest = new Guest();

        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setGuest(guest);

        assertEquals(login, user.getLogin());
        assertEquals(password, user.getPassword());
        assertEquals(guest, user.getGuest());
    }
}