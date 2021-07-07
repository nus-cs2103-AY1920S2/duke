import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class DeadlineTaskTest {

    @Test
    void testCreateDeadlineTask_success() throws ParseException {
        // 1234 becomes 0034
        assertEquals("[D] [❌] Buy birthday present (by: Sat 2345, Sep 9th, 2000)",
                DeadlineTask.createDeadlineTask("Buy birthday present", "09/09/2000 2345").toString());
    }

    @Test
    void testCreateDeadlineTask_invalidDate_exceptionThrown() throws ParseException {
        try {
            assertEquals("[D] [❌] Buy birthday present (by: Sat 2345, Sep 9th, 2000)",
                    DeadlineTask.createDeadlineTask("Buy birthday present", "09-09-2000 2345").toString());
            fail();
        } catch (InvalidTaskDateTimeException e) {
            assertEquals("   The date and/or time format is invalid.\n"
                    + "   Please format your input as: DD/MM/YYYY HHmm.", e.getMessage());
        }

    }
}
