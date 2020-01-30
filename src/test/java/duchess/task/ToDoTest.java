package duchess.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JUnit test class for {@code ToDo}.
 */
public class ToDoTest {
    /**
     * Tests the inheritance of {@code ToDo} from its superclass {@code Task}.
     */
    @Test
    public void testInheritance() {
        ToDo testToDo = new ToDo("Hello world!");
        assertEquals("Hello world!", testToDo.getDescription());
        assertFalse(testToDo.isCompleted());
        testToDo.toggleIsCompleted();
        assertTrue(testToDo.isCompleted());
        assertTrue(new ToDo("Already completed", true).isCompleted());
    }

    /**
     * Tests the overridden {@code toString()} method.
     */
    @Test
    public void testToString() {
        assertEquals("[T][\u2718] Another test ToDo", new ToDo("Another test ToDo").toString());
        assertEquals("[T][\u2713] Go for a run", new ToDo("Go for a run", true).toString());
    }
}
