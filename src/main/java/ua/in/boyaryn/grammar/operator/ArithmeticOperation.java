package ua.in.boyaryn.grammar.operator;

public enum ArithmeticOperation implements BinaryOperator {

    SUM("+") {
        @Override
        public double action(double left, double right) {
            return left + right;
        }
    },
    SUBTRACT("-") {
        @Override
        public double action(double left, double right) {
            return left - right;
        }
    },
    MULTIPLY("*") {
        @Override
        public double action(double left, double right) {
            return left * right;
        }
    },
    DIVIDE("/") {
        @Override
        public double action(double left, double right) {
            return left / right;
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
