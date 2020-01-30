package duke;

import duke.util.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    void getDescription() {
        Task task = new MockTask("a description");
        assertEquals(task.getDescription(), "a description");
    }

    @Test
    void getStatusIcon() {
        Task task = new MockTask("a description");
        assertEquals(task.getStatusIcon(), ("\u2718"));
        task.markAsDone();
        assertEquals(task.getStatusIcon(), "\u2713");
    }

    @Test
    void markAsDone() {
        Task task = new MockTask("a description");
        assertEquals(task.isDone, false);
        task.markAsDone();
        assertEquals(task.isDone, true);
    }

    @Test
    void testToString() {
        Task task = new MockTask("a description");
        assertEquals(task.toString(), "[\u2718] this is a description");
    }
}
