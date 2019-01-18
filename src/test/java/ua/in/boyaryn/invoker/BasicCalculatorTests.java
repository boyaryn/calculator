package ua.in.boyaryn.invoker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ua.in.boyaryn.client.Observer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class BasicCalculatorTests {

    private Processor calc;
    private MemoryBuffer memoryBuffer;

    @BeforeEach
    void init() {
        calc = new BasicCalculator();
        memoryBuffer = new MemoryBuffer();
        calc.addSubscriber(memoryBuffer);
    }

    @ParameterizedTest
    @MethodSource("calcInputAndOutputProvider")
    void checkCalculatorFeedResponse(List<String> inputs, String output, boolean isError) {
        inputs.forEach(input -> calc.feed(input));
        assertEquals(memoryBuffer.getOutput(), output);
        assertEquals(memoryBuffer.isError(), isError);
    }

    static Stream<Arguments> calcInputAndOutputProvider() {
        return Stream.of(
                arguments(Arrays.asList("5", "8", "+"), "13.0", false),
                arguments(Arrays.asList("5 8 +"), "13.0", false),
                arguments(Arrays.asList("5 8 +", "13 -"), "0.0", false),
                arguments(Arrays.asList("5", "9", "1", "-", "/"), "0.625", false),
                arguments(Arrays.asList("5 8 + 12"), "12.0", false),
                arguments(Arrays.asList(" "), null, false),
                arguments(Arrays.asList("5", "0", "/"), "Division by zero. 5.000000 / 0.000000.", true),
                arguments(Arrays.asList("5", "+"), "Not enough operands for operation + at position 0.", true),
                arguments(Arrays.asList("5", "8", "%"), "% at position 0 is neither number nor arithmetic operation.", true)
        );
    }

    @ParameterizedTest
    @MethodSource("calcInputWithUndoAndOutputProvider")
    void checkCalculatorFeedAndUndoResponse(List<String> inputs, String output, boolean isError) {
        inputs.forEach(input -> calc.feed(input));
        calc.undo();
        assertEquals(memoryBuffer.getOutput(), output);
        assertEquals(memoryBuffer.isError(), isError);
    }

    static Stream<Arguments> calcInputWithUndoAndOutputProvider() {
        return Stream.of(
                arguments(Arrays.asList("5", "8", "+"), "8.0", false),
                arguments(Arrays.asList("5"), "", false),
                arguments(Arrays.asList(), "Nothing to undo.", true)
        );
    }

    class MemoryBuffer implements Observer {
        private String output;
        private boolean error;

        @Override
        public void update(String output, boolean error) {
            this.output = output;
            this.error = error;
        }

        public String getOutput() {
            return output;
        }

        public boolean isError() {
            return error;
        }
    }
}
