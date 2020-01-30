package duchess.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ToDoTest {
    @Test
    public void testInheritance() {
        ToDo testToDo = new ToDo("Hello world!");
        assertEquals("Hello world!", testToDo.getDescription());
        assertFalse(testToDo.isCompleted());
        testToDo.toggleIsCompleted();
        assertTrue(testToDo.isCompleted());
        assertTrue(new ToDo("Already completed", true).isCompleted());
    }

    @Test
    public void testToString() {
        assertEquals("[T][\u2718] Another test ToDo", new ToDo("Another test ToDo").toString());
        assertEquals("[T][\u2713] Go for a run", new ToDo("Go for a run", true).toString());
    }
}
