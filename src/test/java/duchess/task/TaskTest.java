package duchess.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskTest {
    @Test
    public void testGetDescription() {
        assertEquals("This is a test description", new Task("This is a test description").getDescription());
        assertEquals("Same here", new Task("Same here", true).getDescription());
    }

    @Test
    public void testIsCompleted() {
        assertFalse(new Task("Test description").isCompleted());
        assertTrue(new Task("Hello world", true).isCompleted());
    }

    @Test
    public void testToggleCompleted() {
        Task testTask = new Task("Task description");
        assertFalse(testTask.isCompleted());
        testTask.toggleIsCompleted();
        assertTrue(testTask.isCompleted());
        testTask.toggleIsCompleted();
        assertFalse(testTask.isCompleted());
    }

    @Test
    public void testToString() {
        Task testTask = new Task("What's up world!");
        assertEquals("[\u2718] What's up world!", testTask.toString());
        testTask.toggleIsCompleted();
        assertEquals("[\u2713] What's up world!", testTask.toString());

    }

}
