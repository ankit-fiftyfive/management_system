package com.example.management_system.service;

import com.example.management_system.entities.IntrospectResponse;
import com.example.management_system.entities.LoginRequest;
import com.example.management_system.entities.LoginResponse;
import com.example.management_system.entities.TokenRequest;
import org.springframework.http.ResponseEntity;

public interface LoginService {

    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest);

    public ResponseEntity<IntrospectResponse> introspect(TokenRequest requestToken);
}
