package com.unibanco.itau.service;

import com.unibanco.itau.dto.TransactionDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TransactionServiceTest {
    @Test
    public void ShouldReadAndWriteAndDeleteFile() throws IOException {
        DateConversion dateConversion = new DateConversion();
        TransactionService transactionService = new TransactionService(dateConversion);

        String path = "src/main/resources/db/database-test.json";

        TransactionDTO transaction = new TransactionDTO(
                BigDecimal.valueOf(20),
                OffsetDateTime.now()
        );

        var formatedTransaction = dateConversion.convert(transaction);
        transactionService.write(formatedTransaction,path);
        var result = transactionService.read(path);
        assertEquals(formatedTransaction.dataHora(),result.getFirst().dataHora());
        assertEquals(formatedTransaction.valor(),result.getFirst().valor());
        transactionService.deleteFile(path);
        File file = new File(path);
        assertFalse(file.exists());
    }
}
