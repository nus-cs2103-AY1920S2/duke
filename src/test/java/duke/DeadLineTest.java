package duke;

import duke.task.Deadlines;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The Class DeadLineTest.
 */
public class DeadLineTest {

    /**
     * Test deadline format.
     */
    @Test
    public void testDeadlineFormat() {
        LocalDate deadLineDate = LocalDate.parse("2019-02-11");
        LocalTime deadLineTime = LocalTime.parse("17:00");
        Deadlines task = new Deadlines("Return Book", deadLineDate, deadLineTime);
        StringBuilder s = new StringBuilder();
        s.append(task.getDeadlineDate().format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        s.append(task.getDeadlineTime().format(DateTimeFormatter.ofPattern(" h.mm a")));
        assertEquals("Feb 11 2019 5.00 PM", s.toString());
    }


}
