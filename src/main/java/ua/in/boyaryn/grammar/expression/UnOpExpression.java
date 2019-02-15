package ua.in.boyaryn.grammar.expression;

import ua.in.boyaryn.grammar.operator.UnaryOperator;

import java.math.BigDecimal;

public class UnOpExpression implements Expression {

    private final BigDecimal value;

    public UnOpExpression(Expression operand, UnaryOperator operator) {
        this.value = operator.action(operand.evaluate());
    }

    @Override
    public BigDecimal evaluate() {
        return value;
    }
}
