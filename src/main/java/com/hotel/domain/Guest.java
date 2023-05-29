package com.hotel.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import jakarta.persistence.*;

import java.util.List;

@Data
@Table
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String passportNumber;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "GuestReservation",
            joinColumns = {@JoinColumn(name = "guest_id")},
            inverseJoinColumns = {@JoinColumn(name = "reservation_id")})
    private List<Reservation> reservation;

}