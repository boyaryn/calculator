package ua.in.boyaryn.grammar.expression;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ua.in.boyaryn.exception.ProcessingException;
import ua.in.boyaryn.grammar.operator.ArithmeticOperation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BinOpExpressionTests {

    @Test
    void createBinOpExpression() throws ProcessingException {
        BinOpExpression number = new BinOpExpression(new Number(1), new Number(2), ArithmeticOperation.SUM);
        assertEquals(3, number.evaluate());
    }

    @Test
    @DisplayName("throws ProcessingException when dividing by zero")
    void failToCreateBinOpExpression() {
        Throwable exception = assertThrows(ProcessingException.class, () -> {
            BinOpExpression number = new BinOpExpression(new Number(1), new Number(0), ArithmeticOperation.DIVIDE);
        });
        assertEquals("Division by zero.", exception.getMessage());

    }
}
