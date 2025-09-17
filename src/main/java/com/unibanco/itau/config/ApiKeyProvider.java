package com.unibanco.itau.config;

import com.unibanco.itau.service.ApiKeyService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class ApiKeyProvider implements AuthenticationProvider {
    private final ApiKeyService keyService;
    public ApiKeyProvider(ApiKeyService keyService){
        this.keyService = keyService;
    }
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String apiKey = (String) authentication.getCredentials();
        return keyService.findByApiKey(apiKey)
                .map(users -> new ApiKeyAuthenticationToken(users, users.getAuthorities()))
                .orElseThrow(() -> new BadCredentialsException("Invalid API Key"));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return ApiKeyAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
