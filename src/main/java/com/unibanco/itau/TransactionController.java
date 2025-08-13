package com.unibanco.itau;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/transacao")
public class TransactionController {
    @PostMapping
    public ResponseEntity<TransactionDTO> receiveTransaction(@PathVariable TransactionDTO json){
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .build()
                .toUri();
        return ResponseEntity.created(uri).build();
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
