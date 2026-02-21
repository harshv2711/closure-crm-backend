package com.closurecrm.api.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.closurecrm.api.auth.dto.AuthenticationRequest;
import com.closurecrm.api.auth.dto.AuthenticationResponse;
import com.closurecrm.api.auth.dto.RegisterRequest;
import com.closurecrm.api.auth.service.AuthenticationService;
import com.closurecrm.api.common.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(
            ApiResponse.success(service.register(request), "User registered successfully")
        );
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(
            ApiResponse.success(service.authenticate(request), "Login successful")
        );
    }
}