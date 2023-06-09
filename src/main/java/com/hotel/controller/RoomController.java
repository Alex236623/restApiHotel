package com.hotel.controller;

import com.hotel.domain.Room;
import com.hotel.dto.RoomDto;
import com.hotel.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping
    public ResponseEntity<List<RoomDto>> findAll() {
        return ResponseEntity.ok(roomService.findAll());
    }

    @GetMapping("/{id}")
    public RoomDto getRoomById(@PathVariable Long id) {
        return roomService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Room> addRoom(@RequestBody Room room) {
        return new ResponseEntity<>(roomService.addRoom(room), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomDto> updateRoom(@PathVariable Long id, @RequestBody RoomDto room) {
        return new ResponseEntity<>(roomService.updateRoom(id, room), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
    }
}