package com.example.hamro_barber.entity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AppointmentStatus {
    UPCOMING("upcoming"),
    COMPLETED("completed"),
    CANCELED("canceled");

    private String value;

    public String getValue() {
        return this.value;
    }
}
