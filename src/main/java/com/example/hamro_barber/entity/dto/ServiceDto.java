package com.example.hamro_barber.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceDto {
    private Integer id;

    private String serviceName;
    private String fee;
    private String serviceTimeInMinutes;
    private BarberDto barber;
}