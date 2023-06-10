package com.hotel.service;

import com.hotel.domain.User;
import com.hotel.domain.Guest;
import com.hotel.repository.UserRepository;
import com.hotel.exception.ResourceNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveUser() {
        Guest guest  = new Guest();
        User user = new User(1L, "john.doe@example.com", "password",guest);

        userService.saveUser(user);

        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testExistsByLogin() {
        when(userRepository.existsByLogin("john.doe@example.com")).thenReturn(true);

        boolean exists = userService.existsByLogin("john.doe@example.com");

        assertTrue(exists);

        verify(userRepository, times(1)).existsByLogin("john.doe@example.com");
    }

    @Test
    void testLoadUserByLogin() {
        User user = new User(1L, "john.doe@example.com", "password", null);

        when(userRepository.findByLogin("john.doe@example.com")).thenReturn(Optional.of(user));

        User loadedUser = userService.loadUserByLogin("john.doe@example.com");

        assertEquals(user.getId(), loadedUser.getId());
        assertEquals(user.getLogin(), loadedUser.getLogin());
        assertEquals(user.getPassword(), loadedUser.getPassword());

        verify(userRepository, times(1)).findByLogin("john.doe@example.com");
    }

    @Test
    void testLoadUserByLogin_ThrowsExceptionWhenUserNotFound() {
        when(userRepository.findByLogin("john.doe@example.com")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.loadUserByLogin("john.doe@example.com"));

        verify(userRepository, times(1)).findByLogin("john.doe@example.com");
    }

    @Test
    void testFindById() {
        Guest guest  = new Guest();

        User user = new User(1L, "john.doe@example.com", "password",guest);
        Optional<User> optionalUser = Optional.of(user);
        when(userRepository.findById(1L)).thenReturn(optionalUser);

        User foundUser = userService.findById(1L);

        assertEquals(user.getId(), foundUser.getId());
        assertEquals(user.getLogin(), foundUser.getLogin());
        assertEquals(user.getPassword(), foundUser.getPassword());

        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testFindById_ThrowsExceptionWhenUserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.findById(1L));

        verify(userRepository, times(1)).findById(1L);
    }
}