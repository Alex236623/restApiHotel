package com.hotel.service;

import com.hotel.domain.Guest;
import com.hotel.repository.GuestMySqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestService {
    @Autowired
    private GuestMySqlRepository guestRepository;

    public List<Guest> getAllGuests() {
        return guestRepository.findAll();
    }

    public Guest getGuestById(Long id) {
        return (Guest) guestRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Guest", "id", id));
    }

    public Guest addGuest(Guest guest) {
        return guestRepository.save(guest);
    }

    public Guest updateGuest(Long id, Guest guest) {
        Guest existingGuest = (Guest) guestRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Guest", "id", id));
        existingGuest.setFirstName(guest.getFirstName());
        existingGuest.setLastName(guest.getLastName());
        existingGuest.setPassportNumber(guest.getPassportNumber());
        return guestRepository.save(existingGuest);
    }

    public void deleteGuest(Long id) {
        Guest guest = (Guest) guestRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Guest", "id", id));
        guestRepository.delete(guest);
    }
}
