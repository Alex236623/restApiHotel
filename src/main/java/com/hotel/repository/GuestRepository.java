package com.hotel.repository;

import com.hotel.domain.Guest;

import java.util.List;
import java.util.Optional;

public interface GuestRepository {

  
    List<Guest> findAll();

    Optional<Object> findById(Long id);

    Guest save(Guest guest);

    void delete(Guest guest);
}
