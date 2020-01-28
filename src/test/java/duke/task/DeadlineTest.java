package duke.task;

import duke.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {

    @Test
    public void constructorDeadline_validDateTime_success() throws DukeException {
        assertEquals("[D][N] return book (by: Aug 7 2020 1:00PM)",
                new Deadline("return book", "2020-08-07 13:00").toString());
        assertEquals("[D][N] return book (by: Aug 7 2020)",
                new Deadline("return book", "2020-08-07").toString());
    }

    @Test
    public void constructorDeadline_invalidDateTime_exceptionThrown() {
        try {
            assertEquals("[D][N] return book (by: Aug 7 2020 1:00PM)",
                    new Deadline("return book", "2020-08-07 13:").toString());
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! Format is yyyy-mm-dd HH:mm. Time is optional.", e.getMessage());
        }
    }

    @Test
    public void testToSaveName() {
        try {
            assertEquals("D | 0 | return book | 2020-08-07 13:00\n",
                    new Deadline("return book", "2020-08-07 13:00").toSaveName());
        } catch (DukeException e) {
            assertEquals("OOPS!!! Format is yyyy-mm-dd HH:mm. Time is optional.", e.getMessage());
        }
    }

    @Test
    public void testStringConversion() {
        try {
            assertEquals("[D][N] meeting (by: Aug 8 2020 1:15PM)",
                    new Deadline("meeting", "2020-08-08 13:15").toString());
        } catch (DukeException e) {
            assertEquals("OOPS!!! Format is yyyy-mm-dd HH:mm. Time is optional.", e.getMessage());
        }
    }
}
