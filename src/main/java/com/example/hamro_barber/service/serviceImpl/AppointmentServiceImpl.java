package com.example.hamro_barber.service.serviceImpl;

import com.example.hamro_barber.entity.Appointment;
import com.example.hamro_barber.entity.Barber;
import com.example.hamro_barber.entity.Customer;
import com.example.hamro_barber.entity.dto.AppointmentRegisterDto;
import com.example.hamro_barber.exception.CustomException;
import com.example.hamro_barber.exception.ResourceNotFoundException;
import com.example.hamro_barber.helper.ApiResponse;
import com.example.hamro_barber.repository.AppointmentRepository;
import com.example.hamro_barber.service.AppointmentService;
import com.example.hamro_barber.service.BarberService;
import com.example.hamro_barber.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final BarberService barberService;
    private final CustomerService customerService;
    @Override
    public Appointment createAppointment(AppointmentRegisterDto registerDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Barber barber = barberService.findBarberById(registerDto.getBarberId());
        Customer customer = customerService.findCustomerByEmail(auth.getName());
        boolean isBarberAvailable = checkBarberAvailability(registerDto.getBarberId(), registerDto.getBookingStart(), registerDto.getBookingEnd());
        boolean isCustomerAvailable = checkCustomerAvailability(customer.getId(), registerDto.getBookingStart(), registerDto.getBookingEnd());
        if (!isBarberAvailable) {
            throw new CustomException("Barber not available");
        }
        if (!isCustomerAvailable) {
            throw new CustomException("You have another appointment at this time");
        }
        Appointment appointment = new Appointment();
        appointment.setCustomer(customer);
        appointment.setBarber(barber);
        appointment.setBookingEnd(registerDto.getBookingEnd());
        appointment.setBookingStart(registerDto.getBookingStart());
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

    @Override
    public boolean checkBarberAvailability(Integer barberId, Long bookingStart, Long bookingEnd) {
        Optional<Appointment> appointment = appointmentRepository.checkBarberAvailability(barberId, bookingStart, bookingEnd);
        if (appointment.isPresent()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkCustomerAvailability(Integer customerId, Long bookingStart, Long bookingEnd) {
        Optional<Appointment> appointment = appointmentRepository.checkCustomerAvailability(customerId, bookingStart, bookingEnd);
        if (appointment.isPresent()) {
            return false;
        }
        return true;
    }
}
