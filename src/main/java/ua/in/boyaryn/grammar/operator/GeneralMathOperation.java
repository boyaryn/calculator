package ua.in.boyaryn.grammar.operator;

import java.math.BigDecimal;

public enum GeneralMathOperation implements UnaryOperator {

    ABS("abs") {
        @Override
        public BigDecimal action(BigDecimal operand) {
            return operand.abs();
        }
    };

    private final String text;

    GeneralMathOperation(String text) {
        this.text = text;
    }

    public static GeneralMathOperation fromString(String text) {
        for (GeneralMathOperation o: GeneralMathOperation.values()) {
            if (o.text.equalsIgnoreCase(text)) {
                return o;
            }
        }
        return null;
    }
}
