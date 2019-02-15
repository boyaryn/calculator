package ua.in.boyaryn.grammar.expression;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberTests {

    @Test
    void createNumber() {
        Number number = new Number(new BigDecimal(3.14));
        assertEquals(new BigDecimal(3.14), number.evaluate());
    }
}
