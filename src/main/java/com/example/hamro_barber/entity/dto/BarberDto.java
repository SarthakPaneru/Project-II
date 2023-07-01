package com.example.hamro_barber.entity.dto;

import com.example.hamro_barber.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BarberDto {
    private Integer id;

    private String panNo;
    private boolean isOpened;

    private UserDto user;

    private List<ServiceDto> services;
}
