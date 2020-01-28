package duke.task;

import duke.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {

    @Test
    public void constructorEvent_validDateTime_success() throws DukeException {
        assertEquals("[E][N] meeting (at: Aug 7 2020 1:00PM)",
                new Event("meeting", "2020-08-07 13:00").toString());
        assertEquals("[E][N] meeting (at: Aug 7 2020)",
                new Event("meeting", "2020-08-07").toString());
    }

    @Test
    public void constructorEvent_invalidDateTime_exceptionThrown() {
        try {
            assertEquals("[E][N] meeting (at: Aug 7 2020 1:00PM)",
                    new Event("meeting", "2020-08-07 13:").toString());
            fail();
        } catch (DukeException e) {
           assertEquals("OOPS!!! Format is yyyy-mm-dd HH:mm. Time is optional.", e.getMessage());
        }
    }

    @Test
    public void testToSaveName() {
        try {
            assertEquals("E | 0 | meeting | 2020-08-07 13:00\n",
                    new Event("meeting", "2020-08-07 13:00").toSaveName());
        } catch (DukeException e) {
            assertEquals("OOPS!!! Format is yyyy-mm-dd HH:mm. Time is optional.", e.getMessage());
        }
    }

    @Test
    public void testStringConversion() {
        try {
            assertEquals("[E][N] meeting (at: Aug 8 2020 1:15PM)",
                    new Event("meeting", "2020-08-08 13:15").toString());
        } catch (DukeException e) {
            assertEquals("OOPS!!! Format is yyyy-mm-dd HH:mm. Time is optional.", e.getMessage());
        }
    }
}
