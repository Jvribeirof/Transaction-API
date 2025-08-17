package com.unibanco.itau.service;

import com.unibanco.itau.dto.StatisticDTO;
import com.unibanco.itau.dto.TransactionDTO;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class StatisticsService {
    public StatisticDTO generateStatistics(List<TransactionDTO> transactions) {
        if (transactions == null || transactions.isEmpty()) {
            return new StatisticDTO(0, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
        }

        int count = 0;
        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal min = null;
        BigDecimal max = null;

        for (TransactionDTO t : transactions) {
            BigDecimal valor = t.valor();
            count++;
            sum = sum.add(valor);

            if (min == null || valor.compareTo(min) < 0) {
                min = valor;
            }
            if (max == null || valor.compareTo(max) > 0) {
                max = valor;
            }
        }

        BigDecimal avg = sum.divide(BigDecimal.valueOf(count), 2, RoundingMode.HALF_UP);

        return new StatisticDTO(count, sum, avg, min, max);
    }
}