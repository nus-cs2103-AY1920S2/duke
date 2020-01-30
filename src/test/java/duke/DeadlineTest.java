package duke;

import duke.util.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;

public class DeadlineTest {
    @Test
    void getDueDate() {
        Deadline deadline = new Deadline("hello world", LocalDate.parse("01-20-2020"));
        assertEquals(deadline.getDueDate(), LocalDate.parse("01-20-2020"));
    }

    @Test
    void testToString() {
        Deadline deadline = new Deadline("hello world", LocalDate.parse("01-20-2020"));
        assertEquals(deadline.toString(), "[D][✘] hello world (by: Jan 20 2020)");
    }
}
