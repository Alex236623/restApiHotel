package com.hotel.domain;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

@Entity
@Getter
@Setter
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate startDate;

    private LocalDate endDate;
    @ManyToOne
    private Room room;

    @OneToMany(mappedBy = "reservation")
    private List<Guest> guests = new ArrayList<>();


}