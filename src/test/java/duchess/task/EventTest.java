package duchess.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class EventTest {
    @Test
    public void testInheritance() {
        Event testEvent = new Event("Event pizza!", "3-5pm");
        assertEquals("Event pizza!", testEvent.getDescription());
        assertFalse(testEvent.isCompleted());
        testEvent.toggleIsCompleted();
        assertTrue(testEvent.isCompleted());
        assertTrue(new Event("Already completed", "Random time", true).isCompleted());
    }

    @Test
    public void testToString() {
        assertEquals("[E][\u2718] New Event (at: Home)", new Event("New Event", "Home").toString());
        assertEquals("[E][\u2713] Go for a run (at: 3-5pm)",
                new Event("Go for a run", "3-5pm", true).toString());
    }
}
