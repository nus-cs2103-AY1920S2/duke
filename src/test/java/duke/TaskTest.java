package duke;

import duke.util.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    void testGetDescription() {
        Task task = new MockTask("a description");
        assertEquals(task.getDescription(), "a description");
    }

    @Test
    void testGetStatusIcon() {
        Task task = new MockTask("a description");
        assertEquals(task.getStatusIcon(), ("\u2718")); // represents cross
        task.markAsDone();
        assertEquals(task.getStatusIcon(), "\u2713"); // represents tick
    }

    @Test
    void testMarkAsDone() {
        Task task = new MockTask("a description");
        assertEquals(task.isDone, false);
        task.markAsDone();
        assertEquals(task.isDone, true);
    }

    @Test
    void testToString() {
        Task task = new MockTask("a description");
        assertEquals(task.toString(), "[\u2718] a description"); // unicode represents cross
    }
}
