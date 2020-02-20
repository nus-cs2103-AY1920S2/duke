package task;

import org.junit.jupiter.api.Test;

import duke.task.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testStringConversion() {
        assertEquals("[T][✓] hello world", (new Todo("hello world", true)).toString());
    }
}