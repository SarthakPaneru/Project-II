package com.example.hamro_barber.repository;

import com.example.hamro_barber.entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Services, Integer> {
}
