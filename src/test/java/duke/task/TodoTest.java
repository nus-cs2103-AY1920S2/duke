package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests written for the Todo class.
 */
public class TodoTest {
    /**
     * Tests the string conversion for a Todo task.
     */
    @Test
    public void testStringConversion() {
        assertEquals("[T][O] Todo Testing", new Todo("Todo Testing", true).toString());
    }
}