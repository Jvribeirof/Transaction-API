package com.unibanco.itau.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record TransactionDTO(
        @PositiveOrZero(message = "valor must be zero or higher")
        @NotNull(message = "valor must not be null")
        BigDecimal valor,
        @PastOrPresent(message = "dataHora must be in the past or in the present")
        @NotNull(message = "dataHora must not be null")
        OffsetDateTime dataHora
) {
}
