package com.hotel.service;

import com.hotel.domain.Room;
import com.hotel.repository.RoomMySqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RoomService {
    @Autowired
    private RoomMySqlRepository roomRepository;

    public RoomService() {
    }

    public List<Map<String, Object>> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomById(Long id) {
        return (Room) roomRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Room", "id", id));
    }

    public Room addRoom(Room room) {
        return roomRepository.save(room);
    }

    public Room updateRoom(Long id, Room room) {
        Room existingRoom = (Room) roomRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Room", "id", id));
        existingRoom.setRoomNumber(room.getRoomNumber());
        existingRoom.setRoomType(room.getRoomType());
        existingRoom.setOccupancy(room.getOccupancy());
        existingRoom.setPrice(room.getPrice());
        return roomRepository.save(existingRoom);
    }

    public void deleteRoom(Long id) {
        Room room = (Room) roomRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Room", "id", id));
        roomRepository.delete(room);
    }
}
