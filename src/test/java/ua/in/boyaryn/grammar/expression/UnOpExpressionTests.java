package ua.in.boyaryn.grammar.expression;

import org.junit.jupiter.api.Test;
import ua.in.boyaryn.grammar.operator.GeneralMathOperation;
import ua.in.boyaryn.grammar.operator.TrigonometricOperation;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnOpExpressionTests {

    @Test
    void createCosExpression() {
        UnOpExpression number = new UnOpExpression(new Number(new BigDecimal(Math.PI)), TrigonometricOperation.COS);
        assertEquals(new BigDecimal(-1), number.evaluate());
    }

    @Test
    void createAbsExpression() {
        UnOpExpression number = new UnOpExpression(new Number(new BigDecimal(-2)), GeneralMathOperation.ABS);
        assertEquals(new BigDecimal(2), number.evaluate());
    }
}
