package com.example.hamro_barber.service.serviceImpl;

import com.example.hamro_barber.entity.Appointment;
import com.example.hamro_barber.exception.ResourceNotFoundException;
import com.example.hamro_barber.helper.ApiResponse;
import com.example.hamro_barber.repository.AppointmentRepository;
import com.example.hamro_barber.service.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    @Override
    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment getAppointment(Integer appointmentId) {
        Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);
        if (appointment.isPresent()) {
            return appointment.get();
        } else {
            throw new ResourceNotFoundException("Appointment not available");
        }
    }

    @Override
    public List<Appointment> getAppointmentsOfBarber(Integer barberId) {
        List<Appointment> appointmentsByBarberId = appointmentRepository.findAppointmentsByBarber_Id(barberId);
        return appointmentsByBarberId;
    }

    @Override
    public List<Appointment> getAppointmentsOfCustomer(Integer customerId) {
        List<Appointment> appointmentsByCustomerId = appointmentRepository.findAppointmentsByCustomer_Id((customerId));
        return appointmentsByCustomerId;
    }

    @Override
    public Appointment updateAppointment(Appointment appointment) {
        Appointment existingAppointment = getAppointment(appointment.getId());
        existingAppointment.setBarber(appointment.getBarber());
        existingAppointment.setCustomer(appointment.getCustomer());
        existingAppointment.setBookingStart(appointment.getBookingStart());
        existingAppointment.setBookingEnd(appointment.getBookingEnd());
        existingAppointment = appointmentRepository.save(existingAppointment);
        return existingAppointment;
    }

    @Override
    public ApiResponse deleteAppointment(Integer appointmentId) {
        Appointment appointment = getAppointment(appointmentId);
        appointmentRepository.delete(appointment);
        return new ApiResponse(true, "Appointment cancelled");
    }
}
