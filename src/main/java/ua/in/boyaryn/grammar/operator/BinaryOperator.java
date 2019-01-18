package ua.in.boyaryn.grammar.operator;

public interface BinaryOperator extends Operator {
    double action(double left, double right);
}
