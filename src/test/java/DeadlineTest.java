import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import duke.task.Deadline;
import duke.exception.DukeException;

public class DeadlineTest {
    @Test
    public void testGetTaskStatus() throws DukeException {
        Deadline deadlineTest = new Deadline("test", "2020-12-01");
        assertEquals("\u2718", deadlineTest.getStatusIcon());
    }
}
