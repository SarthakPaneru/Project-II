package com.example.hamro_barber.controller;

import com.example.hamro_barber.entity.Customer;
import com.example.hamro_barber.entity.User;
import com.example.hamro_barber.exception.CustomException;
import com.example.hamro_barber.helper.LoginRequest;
import com.example.hamro_barber.helper.SignUpRequest;
import com.example.hamro_barber.service.serviceImpl.BarberServiceImpl;
import com.example.hamro_barber.service.serviceImpl.CustomerServiceImpl;
import com.example.hamro_barber.service.serviceImpl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final UserServiceImpl userService;
    private final CustomerServiceImpl customerService;
    private final BarberServiceImpl barberService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest, HttpServletRequest request) {
        User registeredUser = userService.registerUser(signUpRequest, request);
        Customer customer = new Customer();
        customer.setUser(registeredUser);
        Customer registeredCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<>(registeredCustomer, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return new ResponseEntity<>(userService.loginUser(loginRequest), HttpStatus.OK);
    }
}
