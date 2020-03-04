import exceptions.DukeException;
import org.junit.jupiter.api.Test;
import tasks.DeadlineTask;
import tasks.EventTask;
import tasks.TodoTask;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTester {
    @Test
    public void todoTaskCompleteTest() {
        TodoTask task = new TodoTask("test");
        task.complete();
        assertEquals(true, task.isDone());
    }

    @Test
    public void deadlineTaskCompleteTest() throws DukeException {
        DeadlineTask task = new DeadlineTask("test", "01/01/2020 1800");
        task.complete();
        assertEquals(true, task.isDone());
    }

    @Test
    public void eventTaskCompleteTest() throws DukeException {
        EventTask task = new EventTask("test", "01/01/2020 1800 to 01/01/2020 1900");
        task.complete();
        assertEquals(true, task.isDone());
    }
}
