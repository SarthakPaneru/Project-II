package com.example.hamro_barber.repository;

import com.example.hamro_barber.model.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Services, Integer> {
    @Query(nativeQuery = true,
        value = "SELECT DISTINCT(category) FROM services")
    List<String> getCategories();
}
