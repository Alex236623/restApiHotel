package com.hotel.service;

import com.hotel.domain.Room;
import com.hotel.dto.RoomDto;
import com.hotel.repository.RoomRepository;
import com.hotel.exception.ResourceNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class RoomServiceTest {
    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private RoomService roomService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(1L, (long) 100.0, "101", "Standard", "2", 2L, new ArrayList<>()));
        rooms.add(new Room(2L, (long) 150.0, "201", "Deluxe", "3", 2L, new ArrayList<>()));
        when(roomRepository.findAll()).thenReturn(rooms);

        List<RoomDto> roomsDto = roomService.findAll();

        assertEquals(rooms.size(), roomsDto.size());

        verify(roomRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Room room = new Room(1L, (long) 100.0, "101", "Standard", "2", 2L, new ArrayList<>());
        when(roomRepository.findById(1L)).thenReturn(Optional.of(room));

        RoomDto roomDto = roomService.findById(1L);

        assertEquals(room.getId(), roomDto.getId());
        assertEquals(room.getPrice(), roomDto.getPrice());
        assertEquals(room.getRoomNumber(), roomDto.getRoomNumber());
        assertEquals(room.getRoomType(), roomDto.getRoomType());
        assertEquals(room.getOccupancy(), roomDto.getOccupancy());
        assertEquals(room.getNumberOfBeds(), roomDto.getNumberOfBeds());
        assertEquals(room.getReservations().size(), roomDto.getReservations().size());

        verify(roomRepository, times(1)).findById(1L);
    }

    @Test
    void testFindById_ThrowsExceptionWhenRoomNotFound() {
        when(roomRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> roomService.findById(1L));

        verify(roomRepository, times(1)).findById(1L);
    }

    @Test
    void testAddRoom() {
        Room room = new Room(1L, (long) 100.0, "101", "Standard", "2", 2L, new ArrayList<>());

        when(roomRepository.save(room)).thenReturn(room);

        Room addedRoom = roomService.addRoom(room);

        assertEquals(room, addedRoom);

        verify(roomRepository, times(1)).save(room);
    }

    @Test
    void testUpdateRoom() {
        Room room = new Room(1L, (long) 100.0, "101", "Standard", "2", 2L, new ArrayList<>());
        Optional<Room> optionalRoom = Optional.of(room);
        when(roomRepository.findById(1L)).thenReturn(optionalRoom);
        when(roomRepository.save(room)).thenReturn(room);

        RoomDto updatedRoomDto = new RoomDto(1L, (long)150.0, "101", "Deluxe", "3", 2L, new ArrayList<>());

        RoomDto updatedRoom = roomService.updateRoom(1L, updatedRoomDto);

        assertEquals(updatedRoomDto.getId(), updatedRoom.getId());
        assertEquals(updatedRoomDto.getPrice(), updatedRoom.getPrice());
        assertEquals(updatedRoomDto.getRoomNumber(), updatedRoom.getRoomNumber());
        assertEquals(updatedRoomDto.getRoomType(), updatedRoom.getRoomType());
        assertEquals(updatedRoomDto.getOccupancy(), updatedRoom.getOccupancy());
        assertEquals(updatedRoomDto.getNumberOfBeds(), updatedRoom.getNumberOfBeds());
        assertEquals(updatedRoomDto.getReservations().size(), updatedRoom.getReservations().size());

        verify(roomRepository, times(1)).findById(1L);
        verify(roomRepository, times(1)).save(room);
    }

    @Test
    void testUpdateRoom_ThrowsExceptionWhenRoomNotFound() {
        when(roomRepository.findById(1L)).thenReturn(Optional.empty());

        RoomDto updatedRoomDto = new RoomDto(1L, (long)150.0, "101", "Deluxe", "no", 2L, new ArrayList<>());

        assertThrows(ResourceNotFoundException.class, () -> roomService.updateRoom(1L, updatedRoomDto));

        verify(roomRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteRoom() {
        Room room = new Room(1L, (long)100.0, "101", "Standard", "no", 2L, new ArrayList<>());
        when(roomRepository.findById(1L)).thenReturn(Optional.of(room));

        roomService.deleteRoom(1L);

        verify(roomRepository, times(1)).findById(1L);
        verify(roomRepository, times(1)).delete(room);
    }

    @Test
    void testDeleteRoom_ThrowsExceptionWhenRoomNotFound() {
        when(roomRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> roomService.deleteRoom(1L));

        verify(roomRepository, times(1)).findById(1L);
    }
}