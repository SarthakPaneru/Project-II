package com.example.hamro_barber.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AppointmentStatus {
    UPCOMING("upcoming"),
    COMPLETED("completed"),
    CANCELLED("cancelled");

    private String value;

    public String getValue() {
        return this.value;
    }
}
