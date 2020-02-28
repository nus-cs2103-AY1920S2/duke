import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class EventTest {
    @Test
    void write() {
        Event event = new Event("return book", LocalDate.parse("2020-12-10"));
        String expectedOutput = "[E][?] return book (at: Dec 10 2020)";
        assertEquals(event, event);
    }
}
