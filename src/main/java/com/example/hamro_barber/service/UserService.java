package com.example.hamro_barber.service;

import com.example.hamro_barber.entity.User;
import com.example.hamro_barber.helper.AuthResponse;
import com.example.hamro_barber.helper.LoginRequest;
import com.example.hamro_barber.helper.SignUpRequest;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    User loadUserByEmail(String email);
    User registerUser(SignUpRequest signUpRequest, HttpServletRequest request);

    AuthResponse loginUser(LoginRequest loginRequest);
}
