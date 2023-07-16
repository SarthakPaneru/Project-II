package com.example.hamro_barber.repository;

import com.example.hamro_barber.entity.Barber;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface BarberRepository extends JpaRepository<Barber, Integer> {
    Optional<Barber> findBarberByUser_Email(String email);
}
