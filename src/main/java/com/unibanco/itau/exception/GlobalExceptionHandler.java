package com.unibanco.itau.exception;

import com.unibanco.itau.controller.TransactionController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Void> genericHandler(Exception e) {
        logger.info(e.getMessage());
        return ResponseEntity.internalServerError().build();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Void> handlerJsonBadRequest(){
        return ResponseEntity.badRequest().build();
    }
    @ExceptionHandler(UsernameAlreadyExistException.class)
    public ResponseEntity<String> handlerUsernameBadRequest(){
        return ResponseEntity.badRequest().body("Username already exists");
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Void> handlerJsonNotValid(){
        return ResponseEntity.unprocessableEntity().build();
    }
}
