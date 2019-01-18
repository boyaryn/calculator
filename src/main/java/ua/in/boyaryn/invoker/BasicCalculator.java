package ua.in.boyaryn.invoker;

import ua.in.boyaryn.exception.ProcessingException;
import ua.in.boyaryn.grammar.expression.BinOpExpression;
import ua.in.boyaryn.grammar.expression.Expression;
import ua.in.boyaryn.grammar.expression.Number;
import ua.in.boyaryn.grammar.operator.ArithmeticOperation;
import ua.in.boyaryn.grammar.operator.BinaryOperator;

import java.util.EmptyStackException;
import java.util.Stack;

public class BasicCalculator extends Processor {

    private final Stack<Stack<Expression>> history = new Stack<>();

    public void feed(String input) {
        Stack<Expression> expressions = history.empty() ? new Stack<>() : (Stack<Expression>)history.peek().clone();
        boolean error = false;

        String[] tokens = input.split("\\s+");

        for (int i=0; i<tokens.length && !error; i++) {
            String token = tokens[i];
            try {
                double d = Double.parseDouble(token);
                Expression n = new Number(d);
                expressions.push(n);
            } catch (NumberFormatException | NullPointerException nfe) {
                BinaryOperator o = ArithmeticOperation.fromString(token);
                if (o != null) {
                    try {
                        Expression right = expressions.pop();
                        Expression left = expressions.pop();
                        try {
                            Expression e = new BinOpExpression(left, right, o);
                            expressions.push(e);
                        } catch (ProcessingException pe) {
                            String errorResponse = String.format("%s %f / %f.", pe.getMessage(), left.evaluate(), right.evaluate());
                            error = true;
                            notifySubscribers(errorResponse, error);
                        }
                    } catch (EmptyStackException ese) {
                        String errorResponse = String.format("Not enough operands for operation %s at position %d.", token, i);
                        error = true;
                        notifySubscribers(errorResponse, error);
                    }
                } else {
                    String errorResponse = String.format("%s at position %d is neither number nor arithmetic operation.", token, i);
                    error = true;
                    notifySubscribers(errorResponse, error);
                }
            }
        }

        if (!error && !expressions.empty()) {
            history.push(expressions);
            if (!expressions.empty()) {
                notifySubscribers(Double.toString(expressions.peek().evaluate()), error);
            }
        }
    }

    public void undo() {
        if (!history.empty()) {
            history.pop();
            String output = history.empty() ? "" : Double.toString(history.peek().peek().evaluate());
            notifySubscribers(output, false);
        } else {
            String errorResponse = "Nothing to undo.";
            notifySubscribers(errorResponse, true);
        }
    }
}
