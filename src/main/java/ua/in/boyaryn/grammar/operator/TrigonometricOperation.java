package ua.in.boyaryn.grammar.operator;

public enum  TrigonometricOperation implements UnaryOperator {

    COS("cos") {
        @Override
        public double action(double operand) {
            return Math.cos(operand);
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
