import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * EventTest class is a test class for Event class.
 */
public class EventTest {

    /**
     * Tests the event's toString method for the valid format.
     */
    @Test
    public void testEventToString() {
        LocalDate date = LocalDate.parse("2020-02-05", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Task event = new Event("project meeting", date, "2-4pm");
        String expectedFormat = "[E][X] project meeting (at: Feb 5 2020 2-4pm)";
        assertEquals(expectedFormat, event.toString());
    }

}
