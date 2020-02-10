package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JUnit test class for {@code Event}.
 */
public class EventTest {
    /**
     * Tests the inheritance of {@code Event} from its superclass {@code Task}.
     */
    @Test
    public void testInheritance() {
        Event testEvent = new Event("Event pizza!", "3-5pm");
        assertEquals("Event pizza!", testEvent.getDescription());
        assertFalse(testEvent.isCompleted());
        testEvent.toggleIsCompleted();
        assertTrue(testEvent.isCompleted());
        assertTrue(new Event("Already completed", "Random time", true).isCompleted());
    }

    /**
     * Tests the overridden {@code toString()} method.
     */
    @Test
    public void testToString() {
        assertEquals("[E][\u2718] New Event (at: Home)", // cross mark
                new Event("New Event", "Home").toString());
        assertEquals("[E][\u2713] Go for a run (at: 3-5pm)", // tick mark
                new Event("Go for a run", "3-5pm", true).toString());
    }
}
