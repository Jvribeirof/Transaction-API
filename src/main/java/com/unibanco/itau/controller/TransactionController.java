package com.unibanco.itau.controller;

import com.unibanco.itau.dto.StatisticDTO;
import com.unibanco.itau.dto.TransactionDTO;
import com.unibanco.itau.service.DateConversion;
import com.unibanco.itau.service.StatisticsService;
import com.unibanco.itau.service.TransactionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    @Autowired
    public DateConversion dateConversion;

    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    String path = "src/main/resources/db/database.json";

    @PostMapping
    public ResponseEntity<Void> receiveTransaction(@RequestBody @Valid TransactionDTO json) throws IOException {
        var convertedJson = dateConversion.convert(json);
        transactionService.write(convertedJson, path);
        logger.info("Transaction created");
        return ResponseEntity.created(URI.create("api/transacao")).build();
    }
    @GetMapping
    public ResponseEntity<StatisticDTO> sendStatistics() throws IOException {
        var data = transactionService.read(path);
        var stats = statisticsService.generateStatistics(data);
        logger.info("Statistics was called");
        return ResponseEntity.ok().body(stats);
    }
    @DeleteMapping
    public ResponseEntity<String> removeAllStatistics() throws FileNotFoundException {
        transactionService.deleteFile(path);
        logger.info("Statistics was deleted");
        return ResponseEntity.ok().build();
    }
}
