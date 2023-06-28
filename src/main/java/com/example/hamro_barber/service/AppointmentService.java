package com.example.hamro_barber.service;

import com.example.hamro_barber.entity.Appointment;
import com.example.hamro_barber.helper.ApiResponse;

import java.util.List;

public interface AppointmentService {
    Appointment createAppointment(Appointment appointment);
    List<Appointment> getAllAppointments();
    Appointment getAppointment(Integer appointmentId);
    List<Appointment> getAppointmentsOfBarber(Integer barberId);
    List<Appointment> getAppointmentsOfCustomer(Integer customerId);
    Appointment updateAppointment(Appointment appointment);
    ApiResponse deleteAppointment(Integer appointmentId);
}
