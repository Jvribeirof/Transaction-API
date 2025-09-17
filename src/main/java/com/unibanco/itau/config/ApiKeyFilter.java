package com.unibanco.itau.config;

import com.unibanco.itau.entity.Users;
import com.unibanco.itau.service.ApiKeyService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class ApiKeyFilter extends OncePerRequestFilter {
    private final AuthenticationManager authManager;
    public ApiKeyFilter(AuthenticationManager authenticationManager){
        this.authManager = authenticationManager;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var apiKey = request.getHeader("X-API-KEY");
        if (SecurityContextHolder.getContext().getAuthentication() != null || apiKey == null){
            filterChain.doFilter(request,response);
            return;
        }
        var authRequest = new ApiKeyAuthenticationToken(apiKey);
        var authResponse = authManager.authenticate(authRequest);
        SecurityContextHolder.getContext().setAuthentication(authResponse);
        filterChain.doFilter(request,response);
        return;
    }
}
