import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import duke.task.Event;
import duke.exception.DukeException;

public class EventTest {
    @Test
    public void testToString() throws DukeException {
        Event eventTest = new Event("test", "2020-12-01");
        assertEquals("[E][\u2718] test (at: 01 12 2020)", eventTest.toString());
    }
}
