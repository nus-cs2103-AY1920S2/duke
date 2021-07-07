package duke;

import duke.task.Deadline;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void creationTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        Deadline dl = new Deadline("Hello World!", LocalDateTime.parse("1998-03-15 1530", formatter));
        assertEquals(dl.toString(), "[D][✗] Hello World! (by: 15:30, Mar 15 1998)");
    }

    @Test
    public void saveToStringTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        Deadline dl = new Deadline("Hello World!", LocalDateTime.parse("1998-03-15 1530", formatter));
        assertEquals(dl.toSaveString(), "0 || deadline || Hello World! || 1998-03-15 1530");
    }

    @Test
    public void saveToStringDoneTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        Deadline dl = new Deadline("Hello World!", LocalDateTime.parse("1998-03-15 1530", formatter));
        dl.done();
        assertEquals(dl.toSaveString(), "1 || deadline || Hello World! || 1998-03-15 1530");
    }
}
