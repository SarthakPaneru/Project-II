package com.example.hamro_barber.service.serviceImpl;

import com.example.hamro_barber.entity.User;
import com.example.hamro_barber.exception.BadRequestException;
import com.example.hamro_barber.exception.CustomException;
import com.example.hamro_barber.exception.ResourceNotFoundException;
import com.example.hamro_barber.helper.AuthResponse;
import com.example.hamro_barber.helper.LoginRequest;
import com.example.hamro_barber.helper.SignUpRequest;
import com.example.hamro_barber.repository.UserRepository;
import com.example.hamro_barber.security.TokenProvider;
import com.example.hamro_barber.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    @Override
    public User loadUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new ResourceNotFoundException("User not found with email: " + email);
        }
    }

    @Override
    public User registerUser(SignUpRequest signUpRequest, HttpServletRequest request) {
        try {
            try {
                loadUserByEmail(signUpRequest.getEmail());
            } catch (ResourceNotFoundException e) {

                System.out.println("Hello");

                User user = new User();
                user.setEmail(signUpRequest.getEmail());
                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                user.setPassword(bCryptPasswordEncoder.encode(signUpRequest.getPassword()));
                return userRepository.save(user);
            }
        } catch (Exception ex) {
            throw new CustomException(ex.getLocalizedMessage());
        }
        return null;
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
}
