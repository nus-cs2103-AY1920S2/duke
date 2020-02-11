import duke.task.Deadline;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {

    @Test
    void getBy() {
        Assertions.assertEquals(LocalDate.parse("2020-03-19"),
                new Deadline("read book", "2020-03-19").getBy());
    }

    @Test
    void testToString() {
        assertEquals("[D][✘] finish homework (by: Feb 10 2020)",
                new Deadline("finish homework", "2020-02-10").toString());
    }
}