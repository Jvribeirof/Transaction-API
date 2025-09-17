package com.unibanco.itau.service;

import com.unibanco.itau.entity.ApiKey;
import com.unibanco.itau.entity.Users;
import com.unibanco.itau.repository.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {
    private ApiKeyService apiKeyService;
    private UsersRepository usersRepository;
    private PasswordEncoder passwordEncoder;
    public UsersService(UsersRepository usersRepository, ApiKeyService apiKeyService,PasswordEncoder passwordEncoder){
        this.usersRepository = usersRepository;
        this.apiKeyService = apiKeyService;
        this.passwordEncoder = passwordEncoder;
    }
    public boolean usernameExist(String username){
        return usersRepository.existsByUsername(username);
    }
    public Users saveUser(String username, String password){
        var apiKey = new ApiKey(apiKeyService.generateKey());
        var user = new Users(
                username,
                passwordEncoder.encode(password),
                List.of("USER"),
                apiKey
        );
        apiKey.setUsers(user);
        return usersRepository.save(user);
    }
    public Users saveAdmin(String username, String password){
        var apiKey = new ApiKey(apiKeyService.generateKey());
        var admin = new Users(
                username,
                passwordEncoder.encode(password),
                List.of("USER","ADMIN"),
                apiKey
        );
        apiKey.setUsers(admin);
        return usersRepository.save(admin);
    }
}
