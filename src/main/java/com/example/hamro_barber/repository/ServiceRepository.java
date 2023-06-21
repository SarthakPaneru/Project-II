package com.example.hamro_barber.repository;

import com.example.hamro_barber.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Integer> {
}
