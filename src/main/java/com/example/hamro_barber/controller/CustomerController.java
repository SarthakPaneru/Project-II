package com.example.hamro_barber.controller;

import com.example.hamro_barber.entity.Customer;
import com.example.hamro_barber.service.serviceImpl.CustomerServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@AllArgsConstructor
public class CustomerController {
    private final CustomerServiceImpl customerService;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllCustomers() {
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/get/{customerId}")
    public ResponseEntity<?> getCustomer(@PathVariable Integer customerId) {
        return new ResponseEntity<>(customerService.findCustomerById(customerId), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.createCustomer(customer), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.updateCustomer(customer), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity<?> deletedCustomer(@PathVariable Integer customerId) {
        return new ResponseEntity<>(customerService.deleteCustomer(customerId), HttpStatus.NO_CONTENT);
    }
}
