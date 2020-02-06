package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.Task;
import duke.Event;

public class EventTest {
    @Test
    public void getMnemonic() {
        Task t = new Event("", null, false);
        assertEquals("E", t.getMnemonic());
    }

    @Test
    public void getName() {
        Task t = new Event("abc", null, false);
        assertEquals("abc", t.getName());
    }
}
