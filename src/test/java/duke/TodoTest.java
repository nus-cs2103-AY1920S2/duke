package duke;

import duke.util.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    void testToString() {
        Todo todo = new Todo("a description");
        assertEquals(todo.getDescription(), "a description");
    }
}
