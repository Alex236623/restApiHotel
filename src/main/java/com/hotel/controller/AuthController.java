package com.hotel.controller;

import com.hotel.dto.UserDto;
import com.hotel.domain.User;
import com.hotel.domain.Guest;
import com.hotel.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody UserDto userDto) {
        if (userService.existsByLogin(userDto.getLogin())) {
            return ResponseEntity.badRequest().body("Username is already taken");
        }
        User user = new User();
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        userService.saveUser(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDto userDto) {
        String login = userDto.getLogin();
        String password = userDto.getPassword();
        User user = userService.loadUserByLogin(login);
        if (user == null || !password.equals(user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
        return ResponseEntity.ok("User logged in successfully");
    }

    @PostMapping("/guest/{id}")
    public ResponseEntity<String> addGuest(@RequestBody Guest guest, @PathVariable Long id) {
        User user = userService.findById(id);
        user.setGuest(guest);
        userService.saveUser(user);
        return ResponseEntity.ok("User logged in successfully");
    }
}