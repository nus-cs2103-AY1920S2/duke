import java.time.LocalDate;

import Duke.exception.DukeException;
import Duke.task.Event;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EventTest {

    private static final LocalDate localDate = LocalDate.parse("2019-02-13");
    private static final String time = "6pm";

    private Event newEvent() {
        return new Event("reading event", "at", localDate);
    }

    private Event newEventWithTime() {
        return new Event("reading event", "at", localDate, time);
    }

    @Test
    void taskStateString() {
        assertEquals("[✗]", newEvent().taskStateString());
    }

    @Test
    void markDone() throws DukeException {
        assertEquals(true, newEvent().markDone());
    }

    @Test
    void formattedDate() {
        assertEquals("Feb 13 2019", newEvent().formattedDate());
    }

    @Test
    void testToString() {
        assertEquals("[E][✗] reading event at Feb 13 2019", newEvent().toString());
        assertEquals("[E][✗] reading event at Feb 13 2019 6pm", newEventWithTime().toString());
    }
}