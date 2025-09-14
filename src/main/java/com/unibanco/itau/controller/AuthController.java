package com.unibanco.itau.controller;

import com.unibanco.itau.dto.UserCredentialsDTO;
import com.unibanco.itau.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class AuthController {
    private final JwtService jwtService;
    private AuthenticationManager authenticationManager;
    public AuthController(JwtService service, AuthenticationManager authManager){
        this.jwtService = service;
        this.authenticationManager = authManager;
    }
    @PostMapping
    public String login(@RequestBody UserCredentialsDTO credentials){
        var token = new UsernamePasswordAuthenticationToken(
                credentials.username(),credentials.password()
        );
        Authentication authToken = authenticationManager.authenticate(token);
        UserDetails principal = (UserDetails) authToken.getPrincipal();
        return jwtService.generateToken(principal);
    }
}
