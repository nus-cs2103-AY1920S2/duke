package duke;

import duke.exceptions.InvalidArgumentException;
import duke.task.Deadline;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void addDeadlineTest() throws InvalidArgumentException {
        Deadline deadline = new Deadline("Test Deadline Item", "10/10/2020 1800");
        assertEquals("[D][✘] Test Deadline Item (by: 10 October 2020, 6:00 PM)", deadline.toString());
    }
    @Test
    public void addDoneDeadlineTest() throws InvalidArgumentException  {
        Deadline deadline = new Deadline("Test Deadline Item", "10/10/2020 1800");
        deadline.markAsDone();
        assertEquals("[D][✓] Test Deadline Item (by: 10 October 2020, 6:00 PM)", deadline.toString());
    }
    @Test
    public void addInvalidDateDeadlineTest() {
        Assertions.assertThrows(InvalidArgumentException.class, () -> {
            Deadline deadline = new Deadline("Test Deadline Item", "13/13/2020 1800");
        });
    }
}