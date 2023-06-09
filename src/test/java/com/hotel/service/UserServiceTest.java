package com.hotel.service;

import com.hotel.domain.Guest;
import com.hotel.domain.User;
import com.hotel.repository.UserRepository;
import com.hotel.exception.ResourceNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
        // Create a user
        Guest guest  = new Guest();
        User user = new User(1L, "john.doe@example.com", "password",guest);

        // Invoke the service method
        userService.saveUser(user);

        // Verify that the repository method was called
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testExistsByLogin() {
        // Mock the repository response
        when(userRepository.existsByLogin("john.doe@example.com")).thenReturn(true);

        // Invoke the service method
        boolean exists = userService.existsByLogin("john.doe@example.com");

        // Verify the result
        assertTrue(exists);

        // Verify that the repository method was called
        verify(userRepository, times(1)).existsByLogin("john.doe@example.com");
    }

    @Test
    void testLoadUserByLogin() {
        // Mock the repository response

        User user = new User(1L, "john.doe@example.com", "password", null);

        when(userRepository.findByLogin("john.doe@example.com")).thenReturn(Optional.of(user));

        // Invoke the service method
        User loadedUser = userService.loadUserByLogin("john.doe@example.com");

        // Verify the result
        assertEquals(user.getId(), loadedUser.getId());
        assertEquals(user.getLogin(), loadedUser.getLogin());
        assertEquals(user.getPassword(), loadedUser.getPassword());

        // Verify that the repository method was called
        verify(userRepository, times(1)).findByLogin("john.doe@example.com");
    }

    @Test
    void testLoadUserByLogin_ThrowsExceptionWhenUserNotFound() {
        // Mock the repository response
        when(userRepository.findByLogin("john.doe@example.com")).thenReturn(Optional.empty());

        // Verify that the service method throws ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> userService.loadUserByLogin("john.doe@example.com"));

        // Verify that the repository method was called
        verify(userRepository, times(1)).findByLogin("john.doe@example.com");
    }

    @Test
    void testFindById() {
        // Mock the repository response
        Guest guest  = new Guest();

        User user = new User(1L, "john.doe@example.com", "password",guest);
        Optional<User> optionalUser = Optional.of(user);
        when(userRepository.findById(1L)).thenReturn(optionalUser);

        // Invoke the service method
        User foundUser = userService.findById(1L);

        // Verify the result
        assertEquals(user.getId(), foundUser.getId());
        assertEquals(user.getLogin(), foundUser.getLogin());
        assertEquals(user.getPassword(), foundUser.getPassword());

        // Verify that the repository method was called
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testFindById_ThrowsExceptionWhenUserNotFound() {
        // Mock the repository response
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // Verify that the service method throws ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> userService.findById(1L));

        // Verify that the repository method was called
        verify(userRepository, times(1)).findById(1L);
    }
}