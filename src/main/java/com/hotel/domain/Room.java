package com.hotel.domain;

import lombok.Data;
import lombok.Builder;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.ArrayList;

@Data
@Table
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long price;
    @Column
    private String roomNumber;
    @Column
    private String roomType;
    @Column
    private String occupancy;
    @Column
    private Long numberOfBeds;
    @OneToMany(mappedBy = "room")
    private List<Reservation> reservations = new ArrayList<>();
}