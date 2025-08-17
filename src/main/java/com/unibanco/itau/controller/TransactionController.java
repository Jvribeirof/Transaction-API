package com.unibanco.itau.controller;

import com.unibanco.itau.dto.StatisticDTO;
import com.unibanco.itau.dto.TransactionDTO;
import com.unibanco.itau.service.StatisticsService;
import com.unibanco.itau.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/api/transacao")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    public StatisticsService statisticsService;

    String path = "src/main/resources/db/database-test.json";

    @PostMapping
    public ResponseEntity<Void> receiveTransaction(@RequestBody @Valid TransactionDTO json) throws IOException {
        transactionService.write(json, path);
        return ResponseEntity.created(URI.create("api/transacao")).build();
    }
    @GetMapping
    public ResponseEntity<StatisticDTO> sendStatistics() throws IOException {
        var data = transactionService.read(path);
        var stats = statisticsService.generateStatistics(data);
        return ResponseEntity.ok().body(stats);
    }
    @DeleteMapping
    public ResponseEntity<String> removeAllStatistics() throws FileNotFoundException {
        transactionService.deleteFile(path);
        return ResponseEntity.ok().build();
    }
}
