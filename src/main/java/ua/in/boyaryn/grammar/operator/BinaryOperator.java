package ua.in.boyaryn.grammar.operator;

import java.math.BigDecimal;

public interface BinaryOperator extends Operator {
    BigDecimal action(BigDecimal left, BigDecimal right);
}
