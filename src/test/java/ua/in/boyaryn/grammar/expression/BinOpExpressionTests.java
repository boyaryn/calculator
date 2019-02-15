package ua.in.boyaryn.grammar.expression;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ua.in.boyaryn.exception.ProcessingException;
import ua.in.boyaryn.grammar.operator.ArithmeticOperation;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BinOpExpressionTests {

    @Test
    void createBinOpExpression() throws ProcessingException {
        BinOpExpression number = new BinOpExpression(
                new Number(new BigDecimal(1)),
                new Number(new BigDecimal(2)),
                ArithmeticOperation.SUM
        );
        assertEquals(new BigDecimal(3), number.evaluate());
    }

    @Test
    @DisplayName("throws ProcessingException when dividing by zero")
    void failToCreateBinOpExpression() {
        Throwable exception = assertThrows(ProcessingException.class, () -> {
            BinOpExpression number = new BinOpExpression(
                    new Number(new BigDecimal(1)),
                    new Number(new BigDecimal(0)),
                    ArithmeticOperation.DIVIDE
            );
        });
        assertEquals("Division by zero.", exception.getMessage());

    }
}
