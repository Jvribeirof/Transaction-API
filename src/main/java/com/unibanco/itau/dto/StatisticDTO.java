package com.unibanco.itau.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record StatisticDTO(
        @NotNull(message = "count must not be null")
        Integer count,
        @NotNull(message = "sum must not be null")
        BigDecimal sum,
        @NotNull(message = "avg must not be null")
        BigDecimal avg,
        @NotNull(message = "min must not be null")
        BigDecimal min,
        @NotNull(message = "max must not be null")
        BigDecimal max
) {
}
