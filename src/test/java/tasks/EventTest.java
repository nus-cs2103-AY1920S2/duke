package tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testToString() {
        Event event = new Event("2103 lecture", LocalDate.parse("2020-01-31"));
        assertEquals("[E][✘] 2103 lecture (by: Jan 31 2020)", event.toString());
    }
}
