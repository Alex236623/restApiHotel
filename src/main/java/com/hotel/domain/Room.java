package com.hotel.domain;

import lombok.Data;
import jakarta.persistence.*;

import java.util.List;
import java.util.ArrayList;

@Entity
@Data
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int price;
    private String roomNumber;
    private String roomType;
    private String occupancy;
    private int numberOfBeds;


    @OneToMany(mappedBy = "room")
    private List<Reservation> reservations = new ArrayList<>();


}