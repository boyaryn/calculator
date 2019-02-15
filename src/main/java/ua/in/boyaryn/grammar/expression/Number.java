package ua.in.boyaryn.grammar.expression;

import java.math.BigDecimal;

public class Number implements Expression {

    private final BigDecimal number;

    public Number(BigDecimal number) {
        this.number = number;
    }

    @Override
    public BigDecimal evaluate() {
        return number;
    }
}
