package com.hotel.service;

import com.hotel.domain.GuestReservation;
import com.hotel.exception.ResourceNotFoundException;
import com.hotel.repository.GuestReservationRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

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
    public GuestReservation updateReservation(Long id, GuestReservation guestReservation) {
        GuestReservation existingGuestReservation = guestReservationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Guest", "id", id));
        existingGuestReservation.setGuestId(guestReservation.getGuestId());
        existingGuestReservation.setReservationId(guestReservation.getReservationId());
        return guestReservationRepository.save(guestReservation);
    }
}