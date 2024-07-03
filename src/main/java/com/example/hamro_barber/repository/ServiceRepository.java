package com.example.hamro_barber.repository;

import com.example.hamro_barber.model.Services;
import com.example.hamro_barber.model.User;
import com.example.hamro_barber.model.dto.ServiceDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ServiceRepository extends JpaRepository<Services, Integer> {



    @Query(nativeQuery = true,
        value = "SELECT DISTINCT(category) FROM services")
    List<String> getCategories();

    @Query(nativeQuery = true,
            value = "SELECT category, COUNT(*) AS servicecount " +
            "FROM services WHERE barber_id=?1  " +
            "GROUP BY category")
    List<Map<String,Object>> getCountsByCategory(Integer barber_id);

    @Query(nativeQuery = true,
            value = "SELECT service_name, COUNT(*) AS servicecount " +
                    "FROM services" +
                    "WHERE barber_id=?1  " +
                    "GROUP BY service_name")
    List<Map<String,Object>> getCountsByServiceName(Integer barber_id);

    @Query(nativeQuery = true,
            value = "SELECT s.category, COUNT(DISTINCT aps.appointment_id) AS appointment_count " +
                    "FROM appointment_services AS aps " +
                    "JOIN services AS s ON aps.services_id = s.id " +
                    "GROUP BY s.category " +
                    "ORDER BY appointment_count " +
                    "DESC")
    List<Map<String,Object>> getCountsByCategoryForAppointment(Integer barber_id);

    @Query(nativeQuery = true,
            value = "SELECT s.service_name COUNT(DISTINCT aps.appointment_id) AS appointment_count " +
                    "FROM appointment_services AS aps " +
                    "JOIN services AS s ON aps.services_id = s.id " +
                    "GROUP BY s.category " +
                    "ORDER BY appointment_count " +
                    "DESC")
    List<Map<String,Object>> getCountsByServiceNameForAppointment(Integer barber_id);


}
