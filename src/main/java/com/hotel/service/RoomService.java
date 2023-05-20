package com.hotel.service;

import com.hotel.domain.Room;
import com.hotel.dto.RoomDto;
import com.hotel.domain.Reservation;
import com.hotel.repository.RoomRepository;
import com.hotel.exception.ResourceNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<RoomDto> findAll() {
        return roomRepository.findAll()
                .stream()
                .map(room -> RoomDto.builder()
                        .id(room.getId())
                        .price(room.getPrice())
                        .roomNumber(room.getRoomNumber())
                        .roomType(room.getRoomType())
                        .occupancy(room.getOccupancy())
                        .numberOfBeds(room.getNumberOfBeds())
                        .reservations(room.getReservations().stream()
                                .map(Reservation::getId)
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());
    }

    public RoomDto findById(Long id) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (optionalRoom.isPresent()) {
            Room room = optionalRoom.get();
            return RoomDto.builder()
                    .id(room.getId())
                    .price(room.getPrice())
                    .roomNumber(room.getRoomNumber())
                    .roomType(room.getRoomType())
                    .occupancy(room.getOccupancy())
                    .numberOfBeds(room.getNumberOfBeds())
                    .reservations(room.getReservations().stream()
                            .map(Reservation::getId)
                            .collect(Collectors.toList()))
                    .build();
        } else {
            throw new ResourceNotFoundException("Room", "id", id);
        }
    }

    public Room addRoom(Room room) {
        return roomRepository.save(room);
    }

    public Room updateRoom(Long id, Room room) {
        Room existingRoom = roomRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Room", "id", id));
        existingRoom.setRoomNumber(room.getRoomNumber());
        existingRoom.setRoomType(room.getRoomType());
        existingRoom.setOccupancy(room.getOccupancy());
        existingRoom.setPrice(room.getPrice());
        return roomRepository.save(existingRoom);
    }

    public void deleteRoom(Long id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Room", "id", id));
        roomRepository.delete(room);
    }
}