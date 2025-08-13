package com.unibanco.itau;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record TransactionDTO(
        @Positive
        @NotNull
        BigDecimal valor,
        @PastOrPresent
        @NotNull
        OffsetDateTime time
) {
}
