package duke;

import duke.util.Event;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    void testGetScheduledTime() {
        Event evt = new Event("hello world", LocalDate.parse("2020-01-20"));
        assertEquals(evt.getScheduledTime(), LocalDate.parse("2020-01-20"));
    }

    @Test
    void testToString() {
        Event evt = new Event("hello world", LocalDate.parse("2020-01-20"));
        assertEquals(evt.toString(), "[E][✘] hello world (at: Jan 20 2020)");
    }

}
