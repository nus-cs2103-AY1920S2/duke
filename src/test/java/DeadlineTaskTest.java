import exception.InvalidTaskDateTimeException;
import org.junit.jupiter.api.Test;
import util.task.DeadlineTask;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class DeadlineTaskTest {

    @Test
    void testCreateDeadlineTask_success() {
        // 1234 becomes 0034
        assertEquals("[D] [] Buy birthday present (by: Sat 2345, Sep 9th, 2000)",
                DeadlineTask.createDeadlineTask("Buy birthday present",  new Date(2000, 9, 9, 23, 45)).toString());
    }

    @Test
    void testCreateDeadlineTask_invalidDate_exceptionThrown() {
        try {
            assertEquals("[D] [] Buy birthday present (by: Sat 2345, Sep 9th, 2000)",
                    DeadlineTask.createDeadlineTask("Buy birthday present",  new Date(2000, 9, 9, 23, 45)).toString());
            fail();
        } catch (InvalidTaskDateTimeException e) {
            assertEquals("   The date and/or time format is invalid.\n"
                    + "   Please format your input as: DD/MM/YYYY HHmm.", e.getMessage());
        }

    }
}
