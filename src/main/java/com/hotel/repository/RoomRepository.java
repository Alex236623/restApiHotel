package com.hotel.repository;

import com.hotel.domain.Room;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RoomRepository  {
    public List<Map<String, Object>> findAll();
    public Optional<Object> findById(Long id);
    public Room save(Room room);
    public void delete(Room room);
}
