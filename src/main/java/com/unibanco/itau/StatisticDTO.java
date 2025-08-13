package com.unibanco.itau;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record StatisticDTO(
        @NotNull
        Integer count,
        @NotNull
        BigDecimal sum,
        @NotNull
        BigDecimal avg,
        @NotNull
        BigDecimal min,
        @NotNull
        BigDecimal max
) {
}
