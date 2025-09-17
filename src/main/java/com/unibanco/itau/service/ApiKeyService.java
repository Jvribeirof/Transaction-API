package com.unibanco.itau.service;

import com.unibanco.itau.entity.ApiKey;
import com.unibanco.itau.entity.Users;
import com.unibanco.itau.repository.ApiKeyRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;

@Service
public class ApiKeyService {
    private final ApiKeyRepository keyRepository;
    public ApiKeyService(ApiKeyRepository apiKeyRepository){
        this.keyRepository = apiKeyRepository;
    }
    public String generateKey(){
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[64];
        random.nextBytes(bytes);
        return Base64.getEncoder().encodeToString(bytes);
    }
    public Optional<Users> findByApiKey(String apiKey){
        Optional<ApiKey> key = keyRepository.findByKey(apiKey);
        return key.map(ApiKey::getUsers);
    }
}
