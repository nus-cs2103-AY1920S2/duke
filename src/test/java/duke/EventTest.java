package duke;

import duke.task.Events;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * The Class EventTest.
 */
public class EventTest {

    /**
     * Test event date format.
     */
    @Test
    public void testEventDateFormat() {
        LocalDate eventDate = LocalDate.parse("2019-02-11");
        LocalTime eventStart = LocalTime.parse("12:00");
        LocalTime eventEnd = LocalTime.parse("18:00");
        StringBuilder s = new StringBuilder();
        Events task = new Events("Return Book", eventDate, eventStart, eventEnd);
        s.append(task.getEventDate().format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        s.append(task.getEventStart().format(DateTimeFormatter.ofPattern(" h.mm a")));
        s.append(" to");
        s.append(task.getEventEnd().format(DateTimeFormatter.ofPattern(" h.mm a")));
        assertEquals("Feb 11 2019 12.00 PM to 6.00 PM", s.toString());
    }


}
