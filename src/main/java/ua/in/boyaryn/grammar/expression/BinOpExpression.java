package ua.in.boyaryn.grammar.expression;

import ua.in.boyaryn.exception.ProcessingException;
import ua.in.boyaryn.grammar.operator.ArithmeticOperation;
import ua.in.boyaryn.grammar.operator.BinaryOperator;

import java.math.BigDecimal;

public class BinOpExpression implements Expression {

    private final BigDecimal value;

    public BinOpExpression(Expression left, Expression right, BinaryOperator operator) throws ProcessingException {
        if (!right.evaluate().equals(BigDecimal.ZERO) || operator != ArithmeticOperation.DIVIDE) {
            this.value = operator.action(left.evaluate(), right.evaluate());
        } else {
            throw new ProcessingException("Division by zero.");
        }
    }

    @Override
    public BigDecimal evaluate() {
        return value;
    }
}
