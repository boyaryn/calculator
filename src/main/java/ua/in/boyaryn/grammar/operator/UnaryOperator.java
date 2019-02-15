package ua.in.boyaryn.grammar.operator;

import java.math.BigDecimal;

public interface UnaryOperator extends Operator {
    BigDecimal action(BigDecimal operand);
}
