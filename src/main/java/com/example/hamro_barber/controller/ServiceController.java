package com.example.hamro_barber.controller;

import com.example.hamro_barber.entity.Services;
import com.example.hamro_barber.entity.dto.ServiceDto;
import com.example.hamro_barber.mapper.ServiceMapper;
import com.example.hamro_barber.service.serviceImpl.ServiceServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/service")
public class ServiceController {
    private final ServiceServiceImpl serviceService;
    private final ServiceMapper serviceMapper;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllServices() {
        return new ResponseEntity<>(serviceMapper.listServiceToDto(serviceService.getAllServices()), HttpStatus.OK);
    }

    @GetMapping("/get/{serviceId}")
    public ResponseEntity<?> getService(@PathVariable Integer serviceId) {
        return new ResponseEntity<>(serviceMapper.serviceToDto(serviceService.getService(serviceId)), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createService(@RequestBody Services service) {
        return new ResponseEntity<>(serviceMapper.serviceToDto(serviceService.createService(service)), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateService(@RequestBody ServiceDto service) {
        return new ResponseEntity<>(serviceMapper.serviceToDto(serviceService.updateService(serviceMapper.dtoToService(service))), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{serviceId}")
    public ResponseEntity<?> deletedService(@PathVariable Integer serviceId) {
        return new ResponseEntity<>(serviceService.deleteService(serviceId), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getCategories")
    public ResponseEntity<?> getCategories() {
        return new ResponseEntity<>(serviceService.getCategories(), HttpStatus.OK);
    }
}