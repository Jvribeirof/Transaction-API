package com.unibanco.itau;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionDTOTest {
    private static Validator validator;

    private Set<ConstraintViolation<TransactionDTO>> violations;

    @BeforeAll
    static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @ParameterizedTest
    @ValueSource(doubles = {-10.0, -1.5, 0.0})
    public void valorShouldNotBeNegative(double valor){
        TransactionDTO transaction = new TransactionDTO(
                    BigDecimal.valueOf(valor),
                    OffsetDateTime.now()
        );
        violations = validator.validate(transaction);
        assertFalse(violations.isEmpty());
        assertEquals("deve ser maior que 0",
                violations.iterator().next().getMessage());
    }
    @Test
    public void valorShouldNotBeNull(){
        TransactionDTO transaction = new TransactionDTO(
                    null,
                    OffsetDateTime.now()
        );
        violations = validator.validate(transaction);
        assertFalse(violations.isEmpty());
        assertEquals("não deve ser nulo",
                violations.iterator().next().getMessage());

    }
    @Test
    public void dateShouldNotBeNull(){
        TransactionDTO transaction = new TransactionDTO(
                BigDecimal.valueOf(2.22),
                null
        );
        violations = validator.validate(transaction);
        assertFalse(violations.isEmpty());
        assertEquals("não deve ser nulo",
                violations.iterator().next().getMessage());
    }
    @Test
    public void dateShouldNotBeInTheFuture(){
        TransactionDTO transaction = new TransactionDTO(
                BigDecimal.valueOf(0.01),
                OffsetDateTime.now().plusHours(2)
        );
        violations = validator.validate(transaction);
        assertFalse(violations.isEmpty());
        assertEquals("deve ser uma data no passado ou no presente",
                violations.iterator().next().getMessage());
    }
    @Test
    public void dateShouldBeInThePast(){
        TransactionDTO transaction = new TransactionDTO(
                BigDecimal.valueOf(5),
                OffsetDateTime.now().minusHours(9)
        );
        assertTrue(transaction.time().isBefore(OffsetDateTime.now()));
    }
}
