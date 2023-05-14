package com.hotel.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.hotel.domain.Room;
import org.springframework.jdbc.core.RowMapper;


public class RoomRowMapper implements RowMapper<Room> {
    @Override
    public Room mapRow(ResultSet rs, int rowNum) throws SQLException {
        Room room = new Room();

        room.setId(rs.getLong("id"));
        room.setPrice(rs.getInt("price"));
        room.setRoomNumber(rs.getString("roomNumber"));
        room.setRoomType(rs.getString("roomType"));
        room.setOccupancy(rs.getString("occupancy"));
        room.setNumberOfBeds(rs.getInt("numberOfBeds"));

        return room;
    }
}