import duke.task.Deadline;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {

    @Test
    void getBy() {
        Assertions.assertEquals(LocalDate.parse("2020-03-19"),
                new Deadline("read book", LocalDate.parse("2020-03-19")).getDueDate());
    }

    @Test
    void testToString() {
        assertEquals("[D][✘] finish homework (by: Feb 10 2020)",
                new Deadline("finish homework", LocalDate.parse("2020-02-10")).toString());
    }
}