package com.example.hamro_barber.repository;

import com.example.hamro_barber.model.Barber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BarberRepository extends JpaRepository<Barber, Integer> {
    Optional<Barber> findBarberByUser_Email(String email);
}
