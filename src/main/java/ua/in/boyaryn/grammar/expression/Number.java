package ua.in.boyaryn.grammar.expression;

public class Number implements Expression {

    private final double number;

    public Number(double number) {
        this.number = number;
    }

    @Override
    public double evaluate() {
        return number;
    }
}
