package ua.in.boyaryn.grammar.expression;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberTests {

    @Test
    void createNumber() {
        Number number = new Number(3.14);
        assertEquals(3.14, number.evaluate());
    }
}
