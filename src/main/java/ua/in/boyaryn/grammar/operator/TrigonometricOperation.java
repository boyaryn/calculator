package ua.in.boyaryn.grammar.operator;

import java.math.BigDecimal;

public enum  TrigonometricOperation implements UnaryOperator {

    COS("cos") {
        @Override
        public BigDecimal action(BigDecimal operand) {
            return new BigDecimal(Math.cos(operand.doubleValue()));
        }
    };

    private final String text;

    TrigonometricOperation(String text) {
        this.text = text;
    }

    public static TrigonometricOperation fromString(String text) {
        for (TrigonometricOperation o: TrigonometricOperation.values()) {
            if (o.text.equalsIgnoreCase(text)) {
                return o;
            }
        }
        return null;
    }
}
