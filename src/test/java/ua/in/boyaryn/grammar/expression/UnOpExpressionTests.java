package ua.in.boyaryn.grammar.expression;

import org.junit.jupiter.api.Test;
import ua.in.boyaryn.grammar.operator.GeneralMathOperation;
import ua.in.boyaryn.grammar.operator.TrigonometricOperation;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnOpExpressionTests {

    @Test
    void createCosExpression() {
        UnOpExpression number = new UnOpExpression(new Number(Math.PI), TrigonometricOperation.COS);
        assertEquals(-1, number.evaluate());
    }

    @Test
    void createAbsExpression() {
        UnOpExpression number = new UnOpExpression(new Number(-2), GeneralMathOperation.ABS);
        assertEquals(2, number.evaluate());
    }
}
