package com.example.demo.auth.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequest {

    @NotBlank(message = "First name is required")
    public String firstName;

    @NotBlank(message = "Last name is required")
    public String lastName;

    @Email(message = "Invalid email")
    @NotBlank(message = "Email is required")
    public String email;

    @Size(min = 6, message = "Password must contain at least 6 characters")
    public String password;
}