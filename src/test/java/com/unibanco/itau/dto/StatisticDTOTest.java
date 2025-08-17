package com.unibanco.itau.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class StatisticDTOTest {
    private static Validator validator;

    @BeforeAll
    static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    public void countShouldNotBeNull(){
        StatisticDTO statistic = new StatisticDTO(
                null,
                BigDecimal.valueOf(2),
                BigDecimal.valueOf(3),
                BigDecimal.valueOf(4),
                BigDecimal.valueOf(5)
        );
        Set<ConstraintViolation<StatisticDTO>> violations = validator.validate(statistic);
        assertFalse(violations.isEmpty());
        assertEquals("count must not be null",
                violations.iterator().next().getMessage());
    }
    @Test
    public void sumShouldNotBeNull(){
        StatisticDTO statistic = new StatisticDTO(
                1,
                null,
                BigDecimal.valueOf(3),
                BigDecimal.valueOf(4),
                BigDecimal.valueOf(5)
        );
        Set<ConstraintViolation<StatisticDTO>> violations = validator.validate(statistic);
        assertFalse(violations.isEmpty());
        assertEquals("sum must not be null",
                violations.iterator().next().getMessage());
    }
    @Test
    public void avgShouldNotBeNull(){
        StatisticDTO statistic = new StatisticDTO(
                1,
                BigDecimal.valueOf(2),
                null,
                BigDecimal.valueOf(4),
                BigDecimal.valueOf(5)
        );
        Set<ConstraintViolation<StatisticDTO>> violations = validator.validate(statistic);
        assertFalse(violations.isEmpty());
        assertEquals("avg must not be null",
                violations.iterator().next().getMessage());
    }
    @Test
    public void minShouldNotBeNull(){
        StatisticDTO statistic = new StatisticDTO(
                1,
                BigDecimal.valueOf(2),
                BigDecimal.valueOf(3),
                null,
                BigDecimal.valueOf(5)
        );
        Set<ConstraintViolation<StatisticDTO>> violations = validator.validate(statistic);
        assertFalse(violations.isEmpty());
        assertEquals("min must not be null",
                violations.iterator().next().getMessage());
    }
    @Test
    public void maxShouldNotBeNull(){
        StatisticDTO statistic = new StatisticDTO(
                1,
                BigDecimal.valueOf(2),
                BigDecimal.valueOf(3),
                BigDecimal.valueOf(4),
                null
        );
        Set<ConstraintViolation<StatisticDTO>> violations = validator.validate(statistic);
        assertFalse(violations.isEmpty());
        assertEquals("max must not be null",
                violations.iterator().next().getMessage());
    }
}
