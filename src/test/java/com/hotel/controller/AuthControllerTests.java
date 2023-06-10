package com.hotel.controller;

import com.hotel.dto.UserDto;
import com.hotel.domain.User;
import com.hotel.domain.Guest;
import com.hotel.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AuthControllerTests {

    @Mock
    private UserService userService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSignUp_UsernameTaken() {
        // Mocking the UserService behavior
        UserDto userDto = new UserDto();

        userDto.setLogin("username");
        when(userService.existsByLogin("username")).thenReturn(true);

        // Calling the signUp method
        ResponseEntity<String> response = authController.signUp(userDto);

        // Verifying the response
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Username is already taken", response.getBody());

        // Verifying that UserService methods were called
        verify(userService, times(1)).existsByLogin("username");
        verifyNoMoreInteractions(userService);
    }

    @Test
    void testSignUp_SuccessfulRegistration() {
        // Mocking the UserService behavior
        UserDto userDto = new UserDto();
        userDto.setLogin("username");
        userDto.setPassword("password");
        when(userService.existsByLogin("username")).thenReturn(false);

        // Calling the signUp method
        ResponseEntity<String> response = authController.signUp(userDto);

        // Verifying the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User registered successfully", response.getBody());

        // Verifying that UserService methods were called
        verify(userService, times(1)).existsByLogin("username");
        verify(userService, times(1)).saveUser(any(User.class));
        verifyNoMoreInteractions(userService);
    }

    @Test
    void testLogin_InvalidCredentials() {
        // Mocking the UserService behavior
        UserDto userDto = new UserDto();
        userDto.setLogin("username");
        userDto.setPassword("password");
        when(userService.loadUserByLogin("username")).thenReturn(null);

        // Calling the login method
        ResponseEntity<String> response = authController.login(userDto);

        // Verifying the response
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Invalid username or password", response.getBody());

        // Verifying that UserService methods were called
        verify(userService, times(1)).loadUserByLogin("username");
        verifyNoMoreInteractions(userService);
    }

    @Test
    void testLogin_SuccessfulLogin() {
        // Mocking the UserService behavior
        UserDto userDto = new UserDto();
        userDto.setLogin("username");
        userDto.setPassword("password");
        User user = new User();
        user.setPassword("password");
        when(userService.loadUserByLogin("username")).thenReturn(user);

        // Calling the login method
        ResponseEntity<String> response = authController.login(userDto);

        // Verifying the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User logged in successfully", response.getBody());

        // Verifying that UserService methods were called
        verify(userService, times(1)).loadUserByLogin("username");
        verifyNoMoreInteractions(userService);
    }
    @Test
    void testAddGuest() {
        // Mocking the UserService behavior
        Guest guest = new Guest();
        User user = new User();
        Long userId = 1L;
        when(userService.findById(userId)).thenReturn(user);

        // Calling the addGuest method
        ResponseEntity<String> response = authController.addGuest(guest, userId);

        // Verifying the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User logged in successfully", response.getBody());

        // Verifying that UserService methods were called
        verify(userService, times(1)).findById(userId);
        verify(userService, times(1)).saveUser(user);
        verifyNoMoreInteractions(userService);
    }
}