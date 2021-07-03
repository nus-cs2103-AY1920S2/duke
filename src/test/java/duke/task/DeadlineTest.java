import duke.task.Deadline;
import duke.task.Task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testDeadlineMarkAsDone() {
        Task testDeadlineTask = new Deadline("return book", "Sunday");
        assertEquals("[D][" + duke.task.Task.CROSS + "] " + "return book (by: Sunday)", testDeadlineTask.toString());
        testDeadlineTask.markAsDone();
        assertEquals("[D][" + duke.task.Task.TICK + "] " + "return book (by: Sunday)", testDeadlineTask.toString());
    }
}