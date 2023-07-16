package com.example.hamro_barber.repository;

import com.example.hamro_barber.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    @Override
    List<Appointment> findAll();

    List<Appointment> findAppointmentsByBarber_Id(Integer barberId);
    List<Appointment> findAppointmentsByCustomer_Id(Integer customerId);

    @Query(nativeQuery = true,
        value = "SELECT * from appointment a \n" +
                "where barber_id = :barberId \n" +
                "and (:bookingStart BETWEEN a.booking_start and a.booking_end )\n" +
                "and (:bookingEnd BETWEEN a.booking_start and a.booking_end)\n" +
                "limit 1")
    Optional<Appointment> checkBarberAvailability(@Param("barberId") Integer barberId, @Param("bookingStart")  Long bookingStart, @Param("bookingEnd") Long bookingEnd);

    @Query(nativeQuery = true,
            value = "SELECT * from appointment a \n" +
                    "where customer_id = :customerId \n" +
                    "and (:bookingStart BETWEEN a.booking_start and a.booking_end )\n" +
                    "and (:bookingEnd BETWEEN a.booking_start and a.booking_end)\n" +
                    "limit 1")
    Optional<Appointment> checkCustomerAvailability(@Param("customerId") Integer customerId, @Param("bookingStart")  Long bookingStart, @Param("bookingEnd") Long bookingEnd);
}
