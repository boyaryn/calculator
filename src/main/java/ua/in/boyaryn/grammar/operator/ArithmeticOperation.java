package ua.in.boyaryn.grammar.operator;

import java.math.BigDecimal;

public enum ArithmeticOperation implements BinaryOperator {

    SUM("+") {
        @Override
        public BigDecimal action(BigDecimal left, BigDecimal right) {
            return left.add(right);
        }
    },
    SUBTRACT("-") {
        @Override
        public BigDecimal action(BigDecimal left, BigDecimal right) {
            return left.subtract(right);
        }
    },
    MULTIPLY("*") {
        @Override
        public BigDecimal action(BigDecimal left, BigDecimal right) {
            return left.multiply(right);
        }
    },
    DIVIDE("/") {
        @Override
        public BigDecimal action(BigDecimal left, BigDecimal right) {
            return left.divide(right);
        }
    };

    private final String text;

    ArithmeticOperation(String text) {
        this.text = text;
    }

    public static ArithmeticOperation fromString(String text) {
        for (ArithmeticOperation o: ArithmeticOperation.values()) {
            if (o.text.equalsIgnoreCase(text)) {
                return o;
            }
        }
        return null;
    }
}
