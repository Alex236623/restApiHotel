package com.hotel.service;

import com.hotel.domain.Guest;
import com.hotel.dto.GuestDto;
import com.hotel.repository.GuestRepository;
import org.springframework.stereotype.Service;
import com.hotel.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GuestService {
    @Autowired
    private final GuestRepository guestRepository;

    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public List<GuestDto> findAll() {
        return guestRepository.findAll()
                .stream()
                .map(guest -> GuestDto.builder()
                        .id(guest.getId())
                        .firstName(guest.getFirstName())
                        .lastName(guest.getLastName())
                        .passportNumber(guest.getPassportNumber())
                        .reservation(guest.getReservation().getId())
                        .build())
                .collect(Collectors.toList());
    }

    public GuestDto getGuestById(Long id) {
        Optional<Guest> optionalGuest = guestRepository.findById(id);
        if (optionalGuest.isPresent()) {
            Guest guest = optionalGuest.get();
            return GuestDto.builder()
                    .id(guest.getId())
                    .firstName(guest.getFirstName())
                    .lastName(guest.getLastName())
                    .passportNumber(guest.getPassportNumber())
                    .reservation(guest.getReservation().getId())
                    .build();
        } else {
            throw new ResourceNotFoundException("Guest", "id", id);
        }
    }


    public GuestDto getGuestByFirstName(String firstName) {
        Optional<Guest> optionalGuest = guestRepository.findByFirstName(firstName);
        if (optionalGuest.isPresent()) {
            Guest guest = optionalGuest.get();
            return GuestDto.builder()
                    .id(guest.getId())
                    .firstName(guest.getFirstName())
                    .lastName(guest.getLastName())
                    .passportNumber(guest.getPassportNumber())
                    .reservation(guest.getReservation().getId())
                    .build();
        } else {
            throw new ResourceNotFoundException("Guest", "firstName", firstName);
        }
    }
    public GuestDto getGuestByPassport(String passport) {
        Optional<Guest> optionalGuest = guestRepository.findByPassportNumber(passport);
        if (optionalGuest.isPresent()) {
            Guest guest = optionalGuest.get();
            return GuestDto.builder()
                    .id(guest.getId())
                    .firstName(guest.getFirstName())
                    .lastName(guest.getLastName())
                    .passportNumber(guest.getPassportNumber())
                    .reservation(guest.getReservation().getId())
                    .build();
        } else {
            throw new ResourceNotFoundException("Guest", "passport", passport);
        }
    }
    public Guest addGuest(Guest guest) {
        return guestRepository.save(guest);
    }

    public Guest updateGuest(Long id, Guest guest) {
        Guest existingGuest = guestRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Guest", "id", id));
        existingGuest.setFirstName(guest.getFirstName());
        existingGuest.setLastName(guest.getLastName());
        existingGuest.setPassportNumber(guest.getPassportNumber());
        return guestRepository.save(existingGuest);
    }

    public void deleteGuest(Long id) {
        Guest guest = guestRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Guest", "id", id));
        guestRepository.delete(guest);
    }



    public GuestDto moveGuestToRoom(Long guestId, Long roomId) {
        return null;
    }
}
