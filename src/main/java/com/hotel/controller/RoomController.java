package com.hotel.controller;

import com.hotel.domain.Room;
import com.hotel.dto.RoomDto;
import com.hotel.service.RoomService;
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
    public Room addRoom(@RequestBody Room room) {
        return roomService.addRoom(room);
    }

    @PutMapping("/{id}")
    public Room updateRoom(@PathVariable Long id, @RequestBody Room room) {
        return roomService.updateRoom(id, room);
    }

    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
    }
}