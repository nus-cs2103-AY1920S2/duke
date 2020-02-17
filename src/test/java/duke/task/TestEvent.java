package duke.task;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEvent {
    @Test
    public void testSerialize() {
        assertEquals("E,0,project meeting,2020-06-06T17:00",
                new Event("project meeting", LocalDateTime.parse("2020-06-06T17:00")).serialize());

        Event event = new Event("interview", LocalDateTime.parse("2020-08-08T15:00"));
        event.markAsDone();
        assertEquals("E,1,interview,2020-08-08T15:00", event.serialize());
    }

    @Test
    public void testToString() {
        assertEquals("[E][✘] project meeting (at: 06 Jun 2020, 05:00PM)",
                new Event("project meeting", LocalDateTime.parse("2020-06-06T17:00")).toString());

        Event event = new Event("interview", LocalDateTime.parse("2020-08-08T15:00"));
        event.markAsDone();
        assertEquals("[E][✔] interview (at: 08 Aug 2020, 03:00PM)", event.toString());
    }
}
