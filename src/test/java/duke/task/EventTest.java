package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class EventTest {
    @Test
    public void getDescription_event_returnsDescription() {
        assertEquals("duke", new Event("duke", "today").getDescription());
    }

    @Test
    public void isCompleted_incompleteEvent_returnsFalse() {
        assertFalse(new Event("duke", "today", false).isCompleted());
    }

    @Test
    public void isCompleted_completedEvent_returnsTrue() {
        assertTrue(new Event("duke", "today", true).isCompleted());
    }

    @Test
    public void complete_incompleteEvent_completesTodo() {
        assertTrue(new Event("duke", "today", false).complete().isCompleted());
    }

    @Test
    public void complete_completedEvent_remainsComplete() {
        assertTrue(new Event("duke", "today", false).complete().isCompleted());
    }

    @Test
    public void toString_event_returnsStringRepresentation() {
        assertEquals("[E][✘] duke (at: today)", new Event("duke", "today").toString());
    }
}
