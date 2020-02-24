package duke;

import duke.parser.Parser;
import duke.tasks.Deadline;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void createDeadlineTest() {
        LocalDate date = null;
        date = Parser.parseDate("2020-08-30");

        StringBuilder expected = new StringBuilder();
        expected.append("[D]" + "[\u2718] ");
        expected.append("Assignment 1 (by: Aug 30 2020)");
        assertEquals(expected.toString(),
                new Deadline("Assignment 1", date).toString());
    }
}
