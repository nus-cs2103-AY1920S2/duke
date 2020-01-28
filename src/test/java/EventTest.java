import org.junit.jupiter.api.Test;
import task.Event;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void toStringTest() {
        Event ev = new Event("CS2103T Tutorial", LocalDate.parse("2020-01-29"));
        assertEquals("[E]\u2718 CS2103T Tutorial (at: Jan 29 2020)", ev.toString());
    }

    @Test
    public void isDoneTest() {
        Event ev = new Event("CS2103T Tutorial", LocalDate.parse("2020-01-29"));
        ev.taskDone();
        assertEquals("[E]\u2713 CS2103T Tutorial (at: Jan 29 2020)", ev.toString());
    }
}
