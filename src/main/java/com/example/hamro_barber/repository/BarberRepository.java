package com.example.hamro_barber.repository;

import com.example.hamro_barber.entity.Barber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarberRepository extends JpaRepository<Barber, Integer> {
}
