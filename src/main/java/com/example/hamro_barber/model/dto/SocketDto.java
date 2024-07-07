package com.example.hamro_barber.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class SocketDto {
    private Integer barberId;
    private Integer customerId;
    private Integer serviceId;
    private Double longitude;
    private Double latitude;
    private Double distance;

}
