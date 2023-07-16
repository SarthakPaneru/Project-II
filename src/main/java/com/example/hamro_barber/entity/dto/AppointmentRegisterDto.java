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
public class AppointmentRegisterDto {
    private Integer id;
    private Long bookingStart;
    private Long bookingEnd;
    private Integer barberId;
//    private Integer customerId;
}
