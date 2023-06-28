package com.example.hamro_barber.repository;

import com.example.hamro_barber.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    @Override
    List<Appointment> findAll();

    List<Appointment> findAppointmentsByBarber_Id(Integer barberId);
    List<Appointment> findAppointmentsByCustomer_Id(Integer customerId);
}
