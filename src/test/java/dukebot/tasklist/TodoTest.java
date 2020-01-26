package dukebot.tasklist;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TodoTest {
    @Test
    void testTodo() {
        // Test if getters and setters are working properly
        Task task = new Todo("test");

        assertEquals("Todo", task.getType());
        assertEquals("", task.dateTimeToString());
        assertEquals("test", task.toString());

        assertFalse(task.getDone());
        task.setDone();
        assertTrue(task.getDone());
    }
}
