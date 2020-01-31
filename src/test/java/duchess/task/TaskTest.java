package duchess.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JUnit test class for {@code Task}.
 */
public class TaskTest {
    /**
     * Tests the {@code getDescription()} method.
     */
    @Test
    public void testGetDescription() {
        assertEquals("This is a test description", new Task("This is a test description").getDescription());
        assertEquals("Same here", new Task("Same here", true).getDescription());
    }

    /**
     * Tests the {@code isCompleted()} method.
     */
    @Test
    public void testIsCompleted() {
        assertFalse(new Task("Test description").isCompleted());
        assertTrue(new Task("Hello world", true).isCompleted());
    }

    /**
     * Tests the {@code toggleIsCompleted()} method.
     */
    @Test
    public void testToggleIsCompleted() {
        Task testTask = new Task("Task description");
        assertFalse(testTask.isCompleted());
        testTask.toggleIsCompleted();
        assertTrue(testTask.isCompleted());
        testTask.toggleIsCompleted();
        assertFalse(testTask.isCompleted());
    }

    /**
     * Tests the overridden {@code toString()} method.
     */
    @Test
    public void testToString() {
        Task testTask = new Task("What's up world!");
        assertEquals("[\u2718] What's up world!", testTask.toString()); // cross mark
        testTask.toggleIsCompleted();
        assertEquals("[\u2713] What's up world!", testTask.toString()); // tick mark

    }

}
