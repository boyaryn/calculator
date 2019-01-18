package ua.in.boyaryn.grammar.expression;

import ua.in.boyaryn.exception.ProcessingException;
import ua.in.boyaryn.grammar.operator.ArithmeticOperation;
import ua.in.boyaryn.grammar.operator.BinaryOperator;

public class BinOpExpression implements Expression {

    private final double value;

    public BinOpExpression(Expression left, Expression right, BinaryOperator operator) throws ProcessingException {
        if (right.evaluate() != 0 || operator != ArithmeticOperation.DIVIDE) {
            this.value = operator.action(left.evaluate(), right.evaluate());
        } else {
            throw new ProcessingException("Division by zero.");
        }
    }

    @Override
    public double evaluate() {
        return value;
    }
}
