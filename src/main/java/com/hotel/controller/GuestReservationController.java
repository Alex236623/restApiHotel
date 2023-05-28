package com.hotel.controller;

import com.hotel.domain.Guest;
import com.hotel.domain.GuestReservation;
import com.hotel.dto.GuestDto;
import com.hotel.repository.GuestReservationRepository;
import com.hotel.service.GuestReservationService;
import com.hotel.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gr")
public class GuestReservationController {
    @Autowired
    private GuestReservationService guestReservationService;

    @GetMapping
    public List<GuestReservation> findAll() {
        return guestReservationService.findAll();
    }

    @PostMapping
    public GuestReservation addReservation(@RequestBody GuestReservation guestReservation) {
        return guestReservationService.addReservation(guestReservation);
    }
}
