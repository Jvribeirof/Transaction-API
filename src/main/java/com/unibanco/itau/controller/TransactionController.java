package com.unibanco.itau.controller;

import com.unibanco.itau.dto.StatisticDTO;
import com.unibanco.itau.dto.TransactionDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/transacao")
public class TransactionController {
    @PostMapping
    public ResponseEntity<Void> receiveTransaction(@RequestBody @Valid TransactionDTO json){
        return ResponseEntity.created(URI.create("api/transacao")).build();
    }
    @GetMapping
    public ResponseEntity<StatisticDTO> sendStatistics(){
        return ResponseEntity.ok().build();
    }
    @DeleteMapping
    public ResponseEntity<String> removeAllStatistics() {
        return ResponseEntity.ok().build();
    }
}
