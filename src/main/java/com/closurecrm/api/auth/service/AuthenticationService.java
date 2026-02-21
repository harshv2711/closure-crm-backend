package com.closurecrm.api.auth.service;

import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.closurecrm.api.auth.dto.AuthenticationRequest;
import com.closurecrm.api.auth.dto.AuthenticationResponse;
import com.closurecrm.api.auth.dto.RegisterRequest;
import com.closurecrm.api.auth.entity.User;
import com.closurecrm.api.auth.repository.RoleRepository;
import com.closurecrm.api.auth.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        // 1. Check if user already exists
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists with email: " + request.getEmail());
        }

        // 2. Map and Save
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .enabled(true)
                .roles(Set.of(roleRepository.findByName("ROLE_SALES_REP")
                    .orElseThrow(() -> new RuntimeException("Error: Default Role not found."))))
                .build();

        userRepository.save(user);

        // 3. Generate Token
        var userDetails = new CustomUserDetails(user);
        var jwtToken = jwtService.generateToken(userDetails);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        var userDetails = new CustomUserDetails(user);
        var jwtToken = jwtService.generateToken(userDetails);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}