import exception.InvalidTaskDateTimeException;
import org.junit.jupiter.api.Test;
import util.task.EventTask;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class EventTaskTest {

    @Test
    void testCreateEventTask_success() {
        // 1234 becomes 0034
        assertEquals("[E] [] Birthday (at: Sat 2345, Sep 9th, 2000)",
                EventTask.createEventTask("Birthday", new Date(2000, 9, 9, 23, 45)).toString());
    }

    @Test
    void testCreateEventTask_invalidDate_exceptionThrown() {
        try {
            assertEquals("[E] [] Birthday (by: Sat 2345, Sep 9th, 2000)",
                    EventTask.createEventTask("Birthday",  new Date(2000, 9, 9, 23, 45)).toString());
            fail();
        } catch (InvalidTaskDateTimeException e) {
            assertEquals("   The date and/or time format is invalid.\n"
                    + "   Please format your input as: DD/MM/YYYY HHmm.", e.getMessage());
        }

    }
}
