package duke;

import duke.util.Event;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    void getScheduledTime() {
        Event evt = new Event("hello world", LocalDate.parse("01-20-2020"));
        assertEquals(evt.getScheduledTime() , LocalDate.parse("01-20-2020"));
    }

    @Test
    void testToString() {
        Event evt = new Event("hello world", LocalDate.parse("01-20-2020"));
        assertEquals(evt.toString(), "[E][✘] hello world (at: Jan 20 2020)");
    }

}
