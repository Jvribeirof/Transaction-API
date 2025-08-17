package com.unibanco.itau.service;

import com.unibanco.itau.dto.TransactionDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateConversionTest {
    @Test
    public void ShouldConvertTransaction(){
        DateConversion conversion = new DateConversion();
        TransactionDTO transaction = new TransactionDTO(
                BigDecimal.valueOf(20),
                OffsetDateTime.parse("2020-08-07T12:34:56.789-03:00")
        );
        TransactionDTO convertedTransaction = conversion.convert(transaction);
        assertEquals(OffsetDateTime.parse("2020-08-07T15:34:56.789Z"),convertedTransaction.dataHora());
    }
}
