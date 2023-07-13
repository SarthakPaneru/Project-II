package com.example.hamro_barber.controller;

import com.example.hamro_barber.entity.dto.PasswordChangeDto;
import com.example.hamro_barber.mapper.UserMapper;
import com.example.hamro_barber.service.serviceImpl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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

    @GetMapping("/get/{userId}")
    public ResponseEntity<?> getUserFromUserId(@PathVariable Integer userId) {
        return new ResponseEntity<>(userMapper.userToDto(userService.findUserById(userId)), HttpStatus.OK);
    }



    @GetMapping("/get-logged-in-user")
    public ResponseEntity<?> getLoggedInUser(Principal principal) {
        return new ResponseEntity<>(principal.getName(), HttpStatus.OK);
    }

//    @PutMapping("update-password")
//    public ResponseEntity<?> updatePassword(@RequestBody PasswordChangeDto changePassword) {
//        return new ResponseEntity<>();
//    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>("Deleted", HttpStatus.NO_CONTENT);
    }

}
