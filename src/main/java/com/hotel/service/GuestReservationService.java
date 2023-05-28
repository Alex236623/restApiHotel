package com.hotel.service;

import com.hotel.domain.GuestReservation;
import com.hotel.repository.GuestReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestReservationService {
    @Autowired
    private final GuestReservationRepository guestReservationRepository;

    public GuestReservationService(GuestReservationRepository guestReservationRepository) {
        this.guestReservationRepository = guestReservationRepository;
    }

    public List<GuestReservation> findAll() {
        return guestReservationRepository.findAll();
    }

    public GuestReservation addReservation(GuestReservation guestReservation) {
        return guestReservationRepository.save(guestReservation);
    }
}
