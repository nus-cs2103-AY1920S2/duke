package duke;

import duke.util.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;

public class DeadlineTest {
    @Test
    void testGetDueDate() {
        Deadline deadline = new Deadline("hello world", LocalDate.parse("2020-01-20"));
        assertEquals(deadline.getDueDate(), LocalDate.parse("2020-01-20"));
    }

    @Test
    void testToString() {
        Deadline deadline = new Deadline("hello world", LocalDate.parse("2020-01-20"));
        assertEquals(deadline.toString(), "[D][✘] hello world (by: Jan 20 2020)");
    }
}
