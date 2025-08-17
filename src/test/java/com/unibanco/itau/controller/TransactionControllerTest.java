package com.unibanco.itau.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unibanco.itau.dto.StatisticDTO;
import com.unibanco.itau.dto.TransactionDTO;
import com.unibanco.itau.service.DateConversion;
import com.unibanco.itau.service.StatisticsService;
import com.unibanco.itau.service.TransactionService;
import org.junit.jupiter.api.Test;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.LinkedList;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @MockitoBean
    private TransactionService transactionService;
    @MockitoBean
    private StatisticsService statisticsService;
    @MockitoBean
    private DateConversion dateConversion;

    @Test
    public void postTransaction_Returns201_WhenCreateTransaction() throws Exception {
        String path = "src/main/resources/db/database-test.json";
        TransactionDTO transaction = new TransactionDTO(
                BigDecimal.valueOf(20),
                OffsetDateTime.parse("2020-08-07T15:34:56.789Z")
        );
        when(dateConversion.convert(transaction)).thenReturn(transaction);
        doNothing().when(transactionService).write(transaction, path);
        String expectedJson = mapper.writeValueAsString(transaction);

        mockMvc.perform(post("/api/transacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(expectedJson))
                .andExpect(status().isCreated());

        verify(transactionService).write(eq(transaction), anyString());
    }

    @Test
    public void getTransaction_Return200_WhenShowStatistics() throws Exception {
        String path = "src/main/resources/db/database-test.json";
        StatisticDTO statistic = new StatisticDTO(
                1,BigDecimal.valueOf(2),BigDecimal.valueOf(2),BigDecimal.valueOf(2),BigDecimal.valueOf(2)
        );
        TransactionDTO transaction = new TransactionDTO(
                BigDecimal.valueOf(20),
                OffsetDateTime.parse("2020-08-07T15:34:56.789Z")
        );
        LinkedList<TransactionDTO> list = new LinkedList<>();
        list.add(transaction);

        when(transactionService.read(path)).thenReturn(list);
        when(statisticsService.generateStatistics(list)).thenReturn(statistic);

        String expectedJson = mapper.writeValueAsString(statistic);

        mockMvc.perform(get("/api/transacao"))
                .andExpect(status().isOk());
        verify(transactionService).read(anyString());
        verify(statisticsService).generateStatistics(anyList());
    }

    @Test
    public void deleteTransaction_Return200_WhenDeleteStatistics() throws Exception {
        String path = "src/main/resources/db/database-test.json";
        doNothing().when(transactionService).deleteFile(path);
        mockMvc.perform(delete("/api/transacao"))
                .andExpect(status().isOk());
        verify(transactionService).deleteFile(anyString());
    }
}
