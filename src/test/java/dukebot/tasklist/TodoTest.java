package dukebot.tasklist;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TodoTest {
    @Test
    public void testTodo() {
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
