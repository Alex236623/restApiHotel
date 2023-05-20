package com.hotel.domain;

import lombok.*;
import jakarta.persistence.*;

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
    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    public String guestShortCard() {
        return this.id + " " + this.getFirstName() + " " + this.getLastName();
    }
}