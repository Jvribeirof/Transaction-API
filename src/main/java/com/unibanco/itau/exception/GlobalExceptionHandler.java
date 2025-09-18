package com.unibanco.itau.exception;

import com.unibanco.itau.controller.TransactionController;
import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Void> genericHandler(Exception e) {
        logger.info(e.getMessage());
        return ResponseEntity.internalServerError().build();
    }

    @ExceptionHandler({
            AuthenticationException.class,
            BadCredentialsException.class,
            CredentialsExpiredException.class,
            AuthenticationCredentialsNotFoundException.class,
            JwtException.class})
    public ResponseEntity<String> handlerAuthentication(Exception e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed: " + e.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handlerAccessDenied(Exception e) {
        return ResponseEntity.status(403).body("Authorization failed: " + e.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Void> handlerJsonBadRequest(){
        return ResponseEntity.badRequest().build();
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Void> handlerJsonNotValid(){
        return ResponseEntity.unprocessableEntity().build();
    }
}
