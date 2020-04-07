package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodosTest {

    Task task = new Todo("go to school");

    @Test
    public void markDoneTest() {
        assertFalse(task.isDone());
        task.markAsDone();
        assertTrue(task.isDone());
    }

    @Test
    public void getTaskNameTest() {
        assertEquals(task.getTaskName(), "go to school");
    }
}
