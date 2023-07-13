package com.example.hamro_barber.service.serviceImpl;

import com.example.hamro_barber.entity.User;
import com.example.hamro_barber.entity.dto.UserDto;
import com.example.hamro_barber.exception.BadRequestException;
import com.example.hamro_barber.exception.CustomException;
import com.example.hamro_barber.exception.ResourceNotFoundException;
import com.example.hamro_barber.helper.AuthResponse;
import com.example.hamro_barber.helper.LoginRequest;
import com.example.hamro_barber.helper.SignUpRequest;
import com.example.hamro_barber.mapper.UserMapper;
import com.example.hamro_barber.repository.UserRepository;
import com.example.hamro_barber.security.TokenProvider;
import com.example.hamro_barber.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private final UserMapper userMapper;

    @Override
    public User findUserByEmail(String email) {
        Optional<User> user = userRepository.findUserByEmail(email);
        if (user.isPresent()) {
            System.out.println("User: " + user.get().getEmail());
            return user.get();
        } else {
            throw new ResourceNotFoundException("User not found with email: " + email);
        }
    }

    @Override
    public User registerUser(SignUpRequest signUpRequest, HttpServletRequest request) {
        try {
            findUserByEmail(signUpRequest.getEmail());
        } catch (ResourceNotFoundException e) {

            if (!signUpRequest.getPassword().equals(signUpRequest.getConfirmPassword())) {
                throw new BadRequestException("Password do not match");
            }

            User user = new User();
            user.setEmail(signUpRequest.getEmail());
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            user.setPassword(bCryptPasswordEncoder.encode(signUpRequest.getPassword()));
            user.setPhone(signUpRequest.getPhone());
            user.setFirstName(signUpRequest.getFirstName());
            user.setLastName(signUpRequest.getLastName());

            return userRepository.save(user);
        }
        throw new BadRequestException("User already exists");
    }

    @Override
    public AuthResponse loginUser(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            System.out.println("Here: " + authentication.getName());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = tokenProvider.createToken(authentication);
            return new AuthResponse(token);
        } catch (BadCredentialsException e) {
            throw new BadRequestException("Username or password is invalid");
        }
        catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @Override
    public User findUserById(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()){
            return user.get();
        } else {
            throw new CustomException("User does not exists");
        }
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        user.setImageUrl(user.getImageUrl());
        user.setPhone(user.getPhone());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Integer userId) {
        userRepository.delete(findUserById(userId));
    }
}
