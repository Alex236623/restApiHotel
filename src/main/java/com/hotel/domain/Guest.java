package com.hotel.domain;

import lombok.Setter;
import lombok.Getter;
import jakarta.persistence.*;

@Entity
@Getter
@Setter
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String passportNumber;
    @ManyToOne
    private Reservation reservation;
}