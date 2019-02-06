package ua.in.boyaryn.grammar.expression;

import ua.in.boyaryn.grammar.operator.UnaryOperator;

public class UnOpExpression implements Expression {

    private final double value;

    public UnOpExpression(Expression operand, UnaryOperator operator) {
        this.value = operator.action(operand.evaluate());
    }

    @Override
    public double evaluate() {
        return value;
    }
}
