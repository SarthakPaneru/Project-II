package com.example.hamro_barber.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDto {
    private Integer id;
    private Long bookingStart;
    private Long bookingEnd;
    private BarberDto barber;
    private CustomerDto customer;
}
