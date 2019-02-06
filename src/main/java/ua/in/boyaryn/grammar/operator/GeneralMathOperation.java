package ua.in.boyaryn.grammar.operator;

public enum GeneralMathOperation implements UnaryOperator {

    ABS("abs") {
        @Override
        public double action(double operand) {
            return Math.abs(operand);
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
