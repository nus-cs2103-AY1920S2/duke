package duke;

import duke.tasks.Deadline;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void createDeadlineTest() {
        Date date = null;
        try {
            date = Deadline.parseDate("2020-08-30");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        StringBuilder expected = new StringBuilder();
        expected.append("[D]" + "[\u2718] ");
        expected.append("Assignment 1 (by: Aug 30 2020)");
        assertEquals("[D]", new Deadline("Assignment 1", date).toString());
    }
}
