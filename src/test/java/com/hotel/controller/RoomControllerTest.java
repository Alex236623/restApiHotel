package com.hotel.controller;

import com.hotel.domain.Room;
import com.hotel.dto.RoomDto;
import com.hotel.service.RoomService;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RoomControllerTest {

    @Mock
    private RoomService roomService;

    @InjectMocks
    private RoomController roomController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<RoomDto> expectedRooms = new ArrayList<>();
        when(roomService.findAll()).thenReturn(expectedRooms);

        ResponseEntity<List<RoomDto>> response = roomController.findAll();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedRooms, response.getBody());
        verify(roomService, times(1)).findAll();
    }

    @Test
    void testGetRoomById() {
        Long roomId = 1L;
        RoomDto expectedRoom = new RoomDto();
        when(roomService.findById(roomId)).thenReturn(expectedRoom);

        RoomDto actualRoom = roomController.getRoomById(roomId);

        assertNotNull(actualRoom);
        assertEquals(expectedRoom, actualRoom);
        verify(roomService, times(1)).findById(roomId);
    }

    @Test
    void testAddRoom() {
        Room room = new Room();
        Room savedRoom = new Room();
        when(roomService.addRoom(room)).thenReturn(savedRoom);


        ResponseEntity<Room> response = roomController.addRoom(room);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedRoom, response.getBody());
        verify(roomService, times(1)).addRoom(room);
    }

    @Test
    void testUpdateRoom() {
        Long roomId = 1L;
        RoomDto roomDto = new RoomDto();
        RoomDto updatedRoomDto = new RoomDto();
        when(roomService.updateRoom(roomId, roomDto)).thenReturn(updatedRoomDto);

        ResponseEntity<RoomDto> response = roomController.updateRoom(roomId, roomDto);

        assertNotNull(response);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals(updatedRoomDto, response.getBody());
        verify(roomService, times(1)).updateRoom(roomId, roomDto);
    }

    @Test
    void testDeleteRoom() {
        Long roomId = 1L;

        roomController.deleteRoom(roomId);

        verify(roomService, times(1)).deleteRoom(roomId);
    }
}