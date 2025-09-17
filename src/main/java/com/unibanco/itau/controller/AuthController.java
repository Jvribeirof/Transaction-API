package com.unibanco.itau.controller;

import com.unibanco.itau.dto.UserCredentialsDTO;
import com.unibanco.itau.exception.UsernameAlreadyExistException;
import com.unibanco.itau.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class AuthController {
    private final UsersService usersService;
    public AuthController(UsersService usersService){
        this.usersService = usersService;
    }
    @PostMapping
    public ResponseEntity<String> register(@RequestBody UserCredentialsDTO credentials){
        if (usersService.usernameExist(credentials.username())){
            throw new UsernameAlreadyExistException("Username already exist");
        }
        var user = usersService.saveUser(credentials.username(), credentials.password());
        return ResponseEntity.ok().body(user.getKey().getKey());
    }
}
