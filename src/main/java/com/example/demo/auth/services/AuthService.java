package com.example.demo.auth.services;

import com.example.demo.Exceptions.DuplicateResourceException;
import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.Exceptions.UnauthorizedException;
import com.example.demo.auth.DTO.AuthResponse;
import com.example.demo.auth.DTO.LoginRequest;
import com.example.demo.auth.DTO.RegisterRequest;
import com.example.demo.security.JwtService;
import com.example.demo.user.Entity.User;
import com.example.demo.user.Repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String register(RegisterRequest request) {

        Optional<User> existingUser = userRepository.findByEmail(request.email);

        if (existingUser.isPresent()) {
            throw new DuplicateResourceException(
                    "Email already exists");
        }

        User user = new User();
        user.setFirstName(request.firstName);
        user.setLastName(request.lastName);
        user.setEmail(request.email);
        user.setPassword(passwordEncoder.encode(request.password));
        user.setRole("USER");

        userRepository.save(user);

        return "User registered successfully";
    }

    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.email)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found"));

        if (!passwordEncoder.matches(
                request.password,
                user.getPassword())) {

            throw new UnauthorizedException(
                    "Invalid email or password");
        }
        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponse(token);
    }
}