import duke.task.Deadline;
import duke.exception.DukeException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {
    @Test
    public void testGetStatusIcon() throws DukeException {
        Deadline deadlineTest = new Deadline("test", "2020-12-01");
        assertEquals("\u2718", deadlineTest.getStatusIcon());
    }
    @Test
    public void testGetSaveFormat() throws DukeException {
        Deadline deadlineTest = new Deadline("test", "2020-12-01");
        assertEquals("D_false_test_2020-12-01", deadlineTest.getSaveFormat());
    }
}
