package duchess.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DuchessExceptionTest {
    @Test
    public void testConstructor() {
        assertEquals("Exception! Failed to succeed.",
                new DuchessException("Exception! Failed to succeed.").getMessage());
    }
}
