package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testEventStringConversion(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        Event event = new Event(" read book",
                LocalDateTime.parse("2020-02-02 2020", formatter));
        assertEquals("[E][INCOMPLETE] read book (at: Feb 2 2020 20:20)", event.toString());
        event.done();
        assertEquals("[E][DONE] read book", event.toString());
    }
}
