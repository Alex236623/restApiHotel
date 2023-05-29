package com.hotel.domain;

import lombok.*;
import jakarta.persistence.*;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

@Data
@Table
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private LocalDate startDate;
    @Column
    private LocalDate endDate;
    @ManyToOne
    private Room room;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "GuestReservation",
            joinColumns = {@JoinColumn(name = "reservation_id")},
            inverseJoinColumns = {@JoinColumn(name = "guest_id")})
    private List<Guest> guests = new ArrayList<>();
}