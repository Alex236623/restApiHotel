package com.hotel.controller;

import com.hotel.domain.Guest;

import com.hotel.dto.GuestDto;
import com.hotel.service.GuestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/guests")
public class GuestController {
    @Autowired
    private GuestService guestService;

    @GetMapping
    public List<GuestDto> findAll() {
        return guestService.findAll();
    }

    @GetMapping("/{id}")
    public GuestDto findById(@PathVariable Long id) {
        return guestService.getGuestById(id);
    }

    @GetMapping("/firstName/{firstName}")
    public GuestDto findByFirstName(@PathVariable String firstName) {
        return guestService.getGuestByFirstName(firstName);
    }

    @GetMapping("/passport/{passport}")
    public GuestDto findByPassport(@PathVariable String passport) {
        return guestService.getGuestByPassport(passport);
    }


    @PostMapping
    public ResponseEntity<Guest> addGuest(@RequestBody Guest guest) {
        return ResponseEntity.ok(guestService.addGuest(guest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Guest> updateGuest(@PathVariable Long id, @RequestBody Guest guest) {
        return ResponseEntity.ok(guestService.updateGuest(id, guest));
    }

    @DeleteMapping("/{id}")
    public void deleteGuest(@PathVariable Long id) {
        guestService.deleteGuest(id);
    }
}
