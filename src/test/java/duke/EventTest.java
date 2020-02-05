package duke;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testEvent() {
        LocalDateTime localDateTime = LocalDateTime.parse("2020-02-04T18:00");
        Event event = new Event("newEvent", localDateTime);
        assertEquals("[E][N] newEvent (by: Feb 4 2020 6:00PM)", event.toString());
    }

}
