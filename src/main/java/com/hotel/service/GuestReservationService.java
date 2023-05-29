package com.hotel.service;

import com.hotel.domain.Guest;
import com.hotel.domain.GuestReservation;
import com.hotel.exception.ResourceNotFoundException;
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



    public GuestReservation updateReservation(Long id,GuestReservation guestReservation) {
        GuestReservation existingGuestReservation = guestReservationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Guest", "id", id));
        existingGuestReservation.setGuest_id(guestReservation.getGuest_id());
        existingGuestReservation.setReservation_id(guestReservation.getReservation_id());
        return guestReservationRepository.save(guestReservation);
    }
}