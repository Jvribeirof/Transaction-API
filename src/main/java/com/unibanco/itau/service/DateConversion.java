package com.unibanco.itau.service;

import com.unibanco.itau.dto.TransactionDTO;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;

@Service
public class DateConversion {
    public TransactionDTO convert (TransactionDTO data) {
        return new TransactionDTO(
                data.valor(),
                data.dataHora().withOffsetSameInstant(ZoneOffset.UTC)
        );
    }
}
