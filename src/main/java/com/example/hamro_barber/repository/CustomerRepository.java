package com.example.hamro_barber.repository;

import com.example.hamro_barber.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
