package duke;

import duke.Task;
import duke.Parser;
import duke.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadLineTest {
    @Test
    public void addDeadLineTest() {
        Parser parser = new Parser();
        Deadline deadline = new Deadline("dance", parser.convertToDate("05-06-1997 18:00"));
        assertEquals("[D][\u2718] dance (by: 05-06-1997 18:00)", deadline.toString());
    }

    @Test
    public void doneDeadLineTest() {
        Parser parser = new Parser();
        Deadline deadline = new Deadline("dance", parser.convertToDate("05-06-1997 18:00"));
        deadline.markAsDone();
        assertEquals("[D][\u2713] dance (by: 05-06-1997 18:00)", deadline.toString());
    }
}