package com.example.hamro_barber.service;

import com.example.hamro_barber.model.User;
import com.example.hamro_barber.helper.AuthResponse;
import com.example.hamro_barber.helper.LoginRequest;
import com.example.hamro_barber.helper.SignUpRequest;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {
    User findUserByEmail(String email);
    User registerUser(SignUpRequest signUpRequest, HttpServletRequest request);

    AuthResponse loginUser(LoginRequest loginRequest);

    User findUserById(Integer userId);
    List<User> findAllUsers();

    User updateUser(User user);

    void deleteUser(Integer userId);
    void saveImage(MultipartFile file);
    Resource loadImage(Integer userId);
}
