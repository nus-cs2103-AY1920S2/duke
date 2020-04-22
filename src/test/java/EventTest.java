import duke.commons.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void constructorTest() {
        Event event = new Event("event", false, "Group meeting", "2020-02-28 1000");
        assertEquals(event.toString(), "[ E ] [ ✗ ] Group meeting (at: 28 Feb 2020 10:00");
    }
}
