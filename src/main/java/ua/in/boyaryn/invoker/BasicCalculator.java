package ua.in.boyaryn.invoker;

import ua.in.boyaryn.exception.ProcessingException;
import ua.in.boyaryn.grammar.expression.BinOpExpression;
import ua.in.boyaryn.grammar.expression.Expression;
import ua.in.boyaryn.grammar.expression.Number;
import ua.in.boyaryn.grammar.expression.UnOpExpression;
import ua.in.boyaryn.grammar.operator.*;

import java.math.BigDecimal;
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
                BigDecimal d = new BigDecimal(token);
                Expression n = new Number(d);
                expressions.push(n);
            } catch (NumberFormatException | NullPointerException nfe) {
                BinaryOperator opBinary = ArithmeticOperation.fromString(token);
                if (opBinary != null) {
                    try {
                        Expression right = expressions.pop();
                        Expression left = expressions.pop();
                        try {
                            Expression e = new BinOpExpression(left, right, opBinary);
                            expressions.push(e);
                        } catch (ProcessingException pe) {
                            String errorResponse = String.format("%s %s / %s.", pe.getMessage(), left.evaluate().toString(), right.evaluate().toString());
                            error = true;
                            notifySubscribers(errorResponse, error);
                        }
                    } catch (EmptyStackException ese) {
                        String errorResponse = String.format("Not enough operands for operation %s at position %d.", token, i);
                        error = true;
                        notifySubscribers(errorResponse, error);
                    }
                } else {
                    UnaryOperator opUnary = TrigonometricOperation.fromString(token);
                    if (opUnary != null) {
                        Expression operand = expressions.pop();
                        Expression e = new UnOpExpression(operand, opUnary);
                        expressions.push(e);
                    } else {
                        opUnary = GeneralMathOperation.fromString(token);
                        if (opUnary != null) {
                            Expression operand = expressions.pop();
                            Expression e = new UnOpExpression(operand, opUnary);
                            expressions.push(e);
                        } else {
                            String errorResponse = String.format("%s at position %d is neither number nor arithmetic operation.", token, i);
                            error = true;
                            notifySubscribers(errorResponse, error);
                        }
                    }
                }
            }
        }

        if (!error && !expressions.empty()) {
            history.push(expressions);
            if (!expressions.empty()) {
                BigDecimal value = expressions.peek().evaluate();
                notifySubscribers(value.toString(), error);
            }
        }
    }

    public void undo() {
        if (!history.empty()) {
            history.pop();
            String output = history.empty() ? "" : history.peek().peek().evaluate().toString();
            notifySubscribers(output, false);
        } else {
            String errorResponse = "Nothing to undo.";
            notifySubscribers(errorResponse, true);
        }
    }
}
