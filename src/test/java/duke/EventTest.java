package duke;

import duke.task.Event;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void creationTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        Event ev = new Event("Hello World!", LocalDateTime.parse("1998-03-15 1530", formatter));
        assertEquals(ev.toString(), "[E][✗] Hello World! (at: 15:30, Mar 15 1998)");
    }

    @Test
    public void saveToStringTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        Event ev = new Event("Hello World!", LocalDateTime.parse("1998-03-15 1530", formatter));
        assertEquals(ev.toSaveString(), "0 || event || Hello World! || 1998-03-15 1530");
    }

    @Test
    public void saveToStringDoneTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        Event ev = new Event("Hello World!", LocalDateTime.parse("1998-03-15 1530", formatter));
        ev.done();
        assertEquals(ev.toSaveString(), "1 || event || Hello World! || 1998-03-15 1530");
    }
}
