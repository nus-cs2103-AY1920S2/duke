import org.junit.jupiter.api.Test;
import duke.task.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testTask_Done() {
        Task task = new Task("buy book");
        assertEquals(false, task.getStatus());
        task.markAsDone();
        assertEquals(true, task.getStatus());
    }

    @Test
    public void testTaskStringConversion() {
        Task task = new Task("finish book");
        assertEquals("[✘]finish book", task.toString());
        task.markAsDone();
        assertEquals("[✓]finish book", task.toString());
    }
}