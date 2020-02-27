package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTest {
    @Test
    public void getDescription_event_returnsDescription() {
        assertEquals("duke", new Event("duke", LocalDateTime.parse("2020-03-02 15:00",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))).getDescription());
    }

    @Test
    public void isCompleted_incompleteEvent_returnsFalse() {
        assertFalse(new Event("duke", LocalDateTime.parse("2020-03-02 15:00",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), false).isCompleted());
    }

    @Test
    public void isCompleted_completedEvent_returnsTrue() {
        assertTrue(new Event("duke", LocalDateTime.parse("2020-03-02 15:00",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), true).isCompleted());
    }

    @Test
    public void complete_incompleteEvent_completesTodo() {
        assertTrue(new Event("duke", LocalDateTime.parse("2020-03-02 15:00",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), false).complete().isCompleted());
    }

    @Test
    public void complete_completedEvent_remainsComplete() {
        assertTrue(new Event("duke", LocalDateTime.parse("2020-03-02 15:00",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), false).complete().isCompleted());
    }

    @Test
    public void toString_event_returnsStringRepresentation() {
        assertEquals("[E][✘] duke (at: 2 Mar 2020, 15:00)", new Event("duke", LocalDateTime.parse("2020-03-02 15:00",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))).toString());
    }
}
