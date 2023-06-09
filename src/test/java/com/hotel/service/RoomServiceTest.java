package com.hotel.service;

import com.hotel.domain.Room;
import com.hotel.dto.RoomDto;
import com.hotel.repository.RoomRepository;
import com.hotel.exception.ResourceNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        // Mock the repository response
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(1L, (long) 100.0, "101", "Standard", "2", 2L, new ArrayList<>()));
        rooms.add(new Room(2L, (long) 150.0, "201", "Deluxe", "3", 2L, new ArrayList<>()));
        when(roomRepository.findAll()).thenReturn(rooms);

        // Invoke the service method
        List<RoomDto> roomDtos = roomService.findAll();

        // Verify the result
        assertEquals(rooms.size(), roomDtos.size());

        // Verify that the repository method was called
        verify(roomRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        // Mock the repository response
        Room room = new Room(1L, (long) 100.0, "101", "Standard", "2", 2L, new ArrayList<>());
        when(roomRepository.findById(1L)).thenReturn(Optional.of(room));

        // Invoke the service method
        RoomDto roomDto = roomService.findById(1L);

        // Verify the result
        assertEquals(room.getId(), roomDto.getId());
        assertEquals(room.getPrice(), roomDto.getPrice());
        assertEquals(room.getRoomNumber(), roomDto.getRoomNumber());
        assertEquals(room.getRoomType(), roomDto.getRoomType());
        assertEquals(room.getOccupancy(), roomDto.getOccupancy());
        assertEquals(room.getNumberOfBeds(), roomDto.getNumberOfBeds());
        assertEquals(room.getReservations().size(), roomDto.getReservations().size());

        // Verify that the repository method was called
        verify(roomRepository, times(1)).findById(1L);
    }

    @Test
    void testFindById_ThrowsExceptionWhenRoomNotFound() {
        // Mock the repository response
        when(roomRepository.findById(1L)).thenReturn(Optional.empty());

        // Verify that the service method throws ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> roomService.findById(1L));

        // Verify that the repository method was called
        verify(roomRepository, times(1)).findById(1L);
    }

    @Test
    void testAddRoom() {
        // Create a room
        Room room = new Room(1L, (long) 100.0, "101", "Standard", "2", 2L, new ArrayList<>());

        // Mock the repository response
        when(roomRepository.save(room)).thenReturn(room);

        // Invoke the service method
        Room addedRoom = roomService.addRoom(room);

        // Verify the result
        assertEquals(room, addedRoom);

        // Verify that the repository method was called
        verify(roomRepository, times(1)).save(room);
    }

    @Test
    void testUpdateRoom() {
        // Mock the repository response
        Room room = new Room(1L, (long) 100.0, "101", "Standard", "2", 2L, new ArrayList<>());
        Optional<Room> optionalRoom = Optional.of(room);
        when(roomRepository.findById(1L)).thenReturn(optionalRoom);
        when(roomRepository.save(room)).thenReturn(room);

        // Create an updated room DTO
        RoomDto updatedRoomDto = new RoomDto(1L, (long)150.0, "101", "Deluxe", "3", 2L, new ArrayList<>());

        // Invoke the service method
        RoomDto updatedRoom = roomService.updateRoom(1L, updatedRoomDto);

        // Verify the result
        assertEquals(updatedRoomDto.getId(), updatedRoom.getId());
        assertEquals(updatedRoomDto.getPrice(), updatedRoom.getPrice());
        assertEquals(updatedRoomDto.getRoomNumber(), updatedRoom.getRoomNumber());
        assertEquals(updatedRoomDto.getRoomType(), updatedRoom.getRoomType());
        assertEquals(updatedRoomDto.getOccupancy(), updatedRoom.getOccupancy());
        assertEquals(updatedRoomDto.getNumberOfBeds(), updatedRoom.getNumberOfBeds());
        assertEquals(updatedRoomDto.getReservations().size(), updatedRoom.getReservations().size());

        // Verify that the repository methods were called
        verify(roomRepository, times(1)).findById(1L);
        verify(roomRepository, times(1)).save(room);
    }

    @Test
    void testUpdateRoom_ThrowsExceptionWhenRoomNotFound() {
        // Mock the repository response
        when(roomRepository.findById(1L)).thenReturn(Optional.empty());

        // Create an updated room DTO
        RoomDto updatedRoomDto = new RoomDto(1L, (long)150.0, "101", "Deluxe", "no", 2L, new ArrayList<>());

        // Verify that the service method throws ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> roomService.updateRoom(1L, updatedRoomDto));

        // Verify that the repository method was called
        verify(roomRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteRoom() {
        // Mock the repository response
        Room room = new Room(1L, (long)100.0, "101", "Standard", "no", 2L, new ArrayList<>());
        when(roomRepository.findById(1L)).thenReturn(Optional.of(room));

        // Invoke the service method
        roomService.deleteRoom(1L);

        // Verify that the repository method was called
        verify(roomRepository, times(1)).findById(1L);
        verify(roomRepository, times(1)).delete(room);
    }

    @Test
    void testDeleteRoom_ThrowsExceptionWhenRoomNotFound() {
        // Mock the repository response
        when(roomRepository.findById(1L)).thenReturn(Optional.empty());

        // Verify that the service method throws ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> roomService.deleteRoom(1L));

        // Verify that the repository method was called
        verify(roomRepository, times(1)).findById(1L);
    }
}