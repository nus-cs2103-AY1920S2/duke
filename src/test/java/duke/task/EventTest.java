package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {
    private final static DateTimeFormatter IN_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    @Test
    void getDateTime() {
        assertEquals("20/02/2020 1900",
                new Event("testing", LocalDateTime.parse("20/02/2020 1900", IN_FORMATTER)).getDateTime());
    }

    @Test
    void testToString() {
        assertEquals("[E][n] testing (at: 20 Feb 2020 07:00 PM)",
                new Event("testing", LocalDateTime.parse("20/02/2020 1900", IN_FORMATTER)).toString());
    }

    @Test
    void saveString() {
        assertEquals("E | 0 | testing | 20/02/2020 1900",
                new Event("testing", LocalDateTime.parse("20/02/2020 1900", IN_FORMATTER)).saveString());
    }
}