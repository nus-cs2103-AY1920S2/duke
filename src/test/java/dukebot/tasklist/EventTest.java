package dukebot.tasklist;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EventTest {
    @Test
    void testEvent() {
        // Test if getters and setters are working properly
        LocalDateTime testTime = LocalDateTime.of(2020,1,2,3,4);
        Task task = new Deadline("test", testTime);

        assertEquals("Event", task.getType());
        // assertEquals("", task.dateTimeToString());
        assertEquals("test" + "(at: " + task.dateTimeToString() + ")", task.toString());

        assertFalse(task.getDone());
        task.setDone();
        assertTrue(task.getDone());
    }
}