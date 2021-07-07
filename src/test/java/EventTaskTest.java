import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class EventTaskTest {

    @Test
    void testCreateEventTask_success() throws ParseException {
        // 1234 becomes 0034
        assertEquals("[E] [❌] Birthday (at: Sat 2345, Sep 9th, 2000)",
                EventTask.createEventTask("Birthday", "09/09/2000 2345").toString());
    }

    @Test
    void testCreateEventTask_invalidDate_exceptionThrown() throws ParseException {
        try {
            assertEquals("[E] [❌] Birthday (by: Sat 2345, Sep 9th, 2000)",
                    EventTask.createEventTask("Birthday", "09-09-2000 2345").toString());
            fail();
        } catch (InvalidTaskDateTimeException e) {
            assertEquals("   The date and/or time format is invalid.\n"
                    + "   Please format your input as: DD/MM/YYYY HHmm.", e.getMessage());
        }

    }
}
