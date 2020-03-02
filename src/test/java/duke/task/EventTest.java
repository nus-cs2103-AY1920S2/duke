package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

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
        testEvent.completeTask();
        assertTrue(testEvent.isCompleted());
        assertTrue(new Event("Already completed", "Random time", true, LocalDateTime.now(),
                LocalDateTime.now()).isCompleted());
    }

    /**
     * Tests the overridden {@code toString()} method.
     */
    @Test
    public void testToString() {
        assertEquals("[E][\u2718] New Event (at: Home)", // cross mark
                new Event("New Event", "Home").toString());
        assertEquals("[E][\u2713] Go for a run (at: 3-5pm)", // tick mark
                new Event("Go for a run", "3-5pm", true, LocalDateTime.now(), LocalDateTime.now()).toString());
    }
}
