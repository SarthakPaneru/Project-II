package com.example.hamro_barber.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Long bookingStart;
    private Long bookingEnd;
    @ManyToOne
    private Barber barber;
    @ManyToOne
    private Customer customer;
    private AppointmentStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    private Services services;
}
