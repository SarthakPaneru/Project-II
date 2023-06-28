package com.example.hamro_barber.controller;

import com.example.hamro_barber.entity.Barber;
import com.example.hamro_barber.service.serviceImpl.BarberServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/barber")
@AllArgsConstructor
public class BarberController {
    private final BarberServiceImpl barberService;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllBarbers() {
        return new ResponseEntity<>(barberService.getAllBarbers(), HttpStatus.OK);
    }

    @GetMapping("/get/{barberId}")
    public ResponseEntity<?> getBarber(@PathVariable Integer barberId) {
        return new ResponseEntity<>(barberService.findBarberById(barberId), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createBarber(@RequestBody Barber barber) {
        return new ResponseEntity<>(barberService.createBarber(barber), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateBarber(@RequestBody Barber barber) {
        return new ResponseEntity<>(barberService.updateBarber(barber), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{barberId}")
    public ResponseEntity<?> deletedBarber(@PathVariable Integer barberId) {
        return new ResponseEntity<>(barberService.deleteBarber(barberId), HttpStatus.NO_CONTENT);
    }
}
