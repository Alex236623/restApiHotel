package com.hotel.controller;
import com.hotel.domain.Room;
import com.hotel.dto.RoomDto;
import com.hotel.service.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class RoomControllerTest {

    @Mock
    private RoomService roomService;

    @InjectMocks
    private RoomController roomController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        // Arrange
        List<RoomDto> expectedRooms = new ArrayList<>();
        when(roomService.findAll()).thenReturn(expectedRooms);

        // Act
        ResponseEntity<List<RoomDto>> response = roomController.findAll();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedRooms, response.getBody());
        verify(roomService, times(1)).findAll();
    }

    @Test
    public void testGetRoomById() {
        // Arrange
        Long roomId = 1L;
        RoomDto expectedRoom = new RoomDto();
        when(roomService.findById(roomId)).thenReturn(expectedRoom);

        // Act
        RoomDto actualRoom = roomController.getRoomById(roomId);

        // Assert
        assertNotNull(actualRoom);
        assertEquals(expectedRoom, actualRoom);
        verify(roomService, times(1)).findById(roomId);
    }

    @Test
    public void testAddRoom() {
        // Arrange
        Room room = new Room();
        Room savedRoom = new Room();
        when(roomService.addRoom(room)).thenReturn(savedRoom);

        // Act
        ResponseEntity<Room> response = roomController.addRoom(room);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedRoom, response.getBody());
        verify(roomService, times(1)).addRoom(room);
    }

    @Test
    public void testUpdateRoom() {
        // Arrange
        Long roomId = 1L;
        RoomDto roomDto = new RoomDto();
        RoomDto updatedRoomDto = new RoomDto();
        when(roomService.updateRoom(roomId, roomDto)).thenReturn(updatedRoomDto);

        // Act
        ResponseEntity<RoomDto> response = roomController.updateRoom(roomId, roomDto);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals(updatedRoomDto, response.getBody());
        verify(roomService, times(1)).updateRoom(roomId, roomDto);
    }

    @Test
    public void testDeleteRoom() {
        // Arrange
        Long roomId = 1L;

        // Act
        roomController.deleteRoom(roomId);

        // Assert
        verify(roomService, times(1)).deleteRoom(roomId);
    }
}