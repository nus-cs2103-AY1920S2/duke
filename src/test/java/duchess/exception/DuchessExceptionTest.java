package duchess.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit test class for {@code DuchessException}.
 */
public class DuchessExceptionTest {
    /**
     * Tests the constructor of {@code DuchessException}.
     */
    @Test
    public void testConstructor() {
        assertEquals("Exception! Failed to succeed.",
                new DuchessException("Exception! Failed to succeed.").getMessage());
    }
}
