package com.unibanco.itau.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unibanco.itau.dto.TransactionDTO;
import com.unibanco.itau.service.TransactionService;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @MockitoBean
    private TransactionService transactionService;

    @BeforeAll
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
    }

    @Test
    public void postTransaction_Returns201_WhenCreateTransaction() throws Exception {
        TransactionDTO transaction = new TransactionDTO(
                BigDecimal.valueOf(20),
                OffsetDateTime.parse( "2020-08-07T15:34:56.789Z")
        );
        String expectedJson = mapper.writeValueAsString(transaction);
        mockMvc.perform(post("/api/transacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(expectedJson))
                .andExpect(status().isCreated());
    }
}
