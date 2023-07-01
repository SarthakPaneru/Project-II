package com.example.hamro_barber.controller;

import com.example.hamro_barber.mapper.UserMapper;
import com.example.hamro_barber.service.serviceImpl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserServiceImpl userService;
    private final UserMapper userMapper;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(userMapper.listUserToDto(userService.findAllUsers()), HttpStatus.OK);
    }

    @GetMapping("get/{userId}")
    public ResponseEntity<?> getUserFromUserId(@PathVariable Integer userId) {
        return new ResponseEntity<>(userMapper.userToDto(userService.findUserById(userId)), HttpStatus.OK);
    }

}
