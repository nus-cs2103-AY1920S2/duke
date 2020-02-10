package tasks;

import duke.tasks.Task;
import duke.tasks.Deadline;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void deadlineTest() {
        Task t = new Deadline("project", "2016-08-23T22:12:45");
        String expected = "[D][\u2718] project (by: 23 Aug 2016, 10:12:45 PM)";
        assertEquals(expected, t.toString());
    }
}
