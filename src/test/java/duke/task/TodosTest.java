package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TodosTest {

    Task task = new Todos("go to school");

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
