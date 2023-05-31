package com.hotel.service;

import com.hotel.domain.Room;
import com.hotel.dto.GuestDto;
import com.hotel.domain.Guest;
import com.hotel.domain.Reservation;
import com.hotel.repository.GuestRepository;
import com.hotel.repository.ReservationRepository;
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
    @Autowired
    private ReservationRepository reservationRepository;


    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public List<GuestDto> findAll() {
        return guestRepository.findAll()
                .stream()
                .map(this::convertToGuestDto)
                .collect(Collectors.toList());
    }

    public GuestDto getGuestById(Long id) {
        Optional<Guest> optionalGuest = guestRepository.findById(id);
        if (optionalGuest.isPresent()) {
            Guest guest = optionalGuest.get();
            return convertToGuestDto(guest);
        } else {
            throw new ResourceNotFoundException("Guest", "id", id);
        }
    }


    public GuestDto getGuestByFirstName(String firstName) {
        Optional<Guest> optionalGuest = guestRepository.findByFirstName(firstName);
        if (optionalGuest.isPresent()) {
            Guest guest = optionalGuest.get();
            return convertToGuestDto(guest);
        } else {
            throw new ResourceNotFoundException("Guest", "firstName", firstName);
        }
    }

    public GuestDto getGuestByPassport(String passport) {
        Optional<Guest> optionalGuest = guestRepository.findByPassportNumber(passport);
        if (optionalGuest.isPresent()) {
            Guest guest = optionalGuest.get();
            return convertToGuestDto(guest);
        } else {
            throw new ResourceNotFoundException("Guest", "passport", passport);
        }
    }

    public Guest saveGuest(Guest guest) {
        Guest savedGuest = guestRepository.save(guest);

        if (guest.getReservation() != null) {

            // Оновити зв'язок між гостем і бронюваннями
            /*for (Reservation reservation : guest.getReservation()) {
                reservation.getGuests().add(savedGuest);
            }*/

            // Зберегти оновлені бронювання в базі даних
            for (Reservation reservation : guest.getReservation()) {
                reservationRepository.save(reservation);
            }
        }
        return savedGuest;
    }

    public GuestDto updateGuest(Long id, GuestDto updatedGuestDto) {
        Optional<Guest> optionalGuest = guestRepository.findById(id);
        if (optionalGuest.isPresent()) {
            Guest existingGuest = optionalGuest.get();
            existingGuest.setFirstName(updatedGuestDto.getFirstName());
            existingGuest.setLastName(updatedGuestDto.getLastName());
            existingGuest.setPassportNumber(updatedGuestDto.getPassportNumber());
            Guest updatedGuest = guestRepository.save(existingGuest);
            return convertToGuestDto(updatedGuest);
        } else {
            throw new ResourceNotFoundException("Guest", "id", id);
        }
    }

    public void deleteGuest(Long id) {
        Guest guest = guestRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Guest", "id", id));
        guestRepository.delete(guest);
    }

    private GuestDto convertToGuestDto(Guest guest) {
        return GuestDto.builder()
                .id(guest.getId())
                .firstName(guest.getFirstName())
                .lastName(guest.getLastName())
                .passportNumber(guest.getPassportNumber())
                .reservation(guest.getReservation().stream()
                        .map(Reservation::getId)
                        .collect(Collectors.toList()))
                .build();
    }
}