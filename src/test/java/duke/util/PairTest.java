package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * JUnit test class for {@code Pair}.
 */
public class PairTest {
    /**
     * Tests the core functionality of the {@code Pair} class.
     */
    @Test
    public void testPair() {
        Pair<Integer, String> testPair = new Pair<>(5, "Hello world!");
        assertEquals(5, testPair.getFirst());
        assertEquals("Hello world!", testPair.getSecond());
    }
}
