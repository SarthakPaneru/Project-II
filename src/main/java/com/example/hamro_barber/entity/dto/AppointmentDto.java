package com.example.hamro_barber.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDto {
    private Integer id;
    private LocalDateTime bookingStart;
    private LocalDateTime bookingEnd;
    private BarberDto barber;
    private CustomerDto customer;
}
