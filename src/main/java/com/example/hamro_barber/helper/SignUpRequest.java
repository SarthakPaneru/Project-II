package com.example.hamro_barber.helper;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class SignUpRequest {
//    @NotBlank
//    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;
    @NotBlank
    private String confirmPassword;

    private String phone;
    private String firstName;
    private String lastName;
    private UserRole userRole;
    private String panNo;
}