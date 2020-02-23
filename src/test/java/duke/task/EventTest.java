package duke.task;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testStringConversion() throws DukeException {
        //a unit test for toString() method of "Event" class
        assertEquals("Event: [Incomplete] Level-9 (at: 1997-05-27T12:22)",
                new Event("Level-9", "27-05-1997 12:22").toString());
    }

    @Test
    public void testGetTimePeriod() throws DukeException {
        //a unit test for getTimePeriod() method of "Event" class
        assertEquals("1997-05-27T12:22",
                new Event("Level-9", "27-05-1997 12:22").getTimePeriod().toString());
    }
}
