package com.example.management_system.controller;

import com.example.management_system.entities.IntrospectResponse;
import com.example.management_system.entities.LoginRequest;
import com.example.management_system.entities.LoginResponse;
import com.example.management_system.entities.TokenRequest;
import com.example.management_system.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class LoginController {
    public final LoginService loginService;
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        return this.loginService.login(loginRequest);
    }
    @PostMapping("/introspect")
    public ResponseEntity<IntrospectResponse> login(@RequestBody TokenRequest requestToken){
        return this.loginService.introspect(requestToken);
    }

}
