package duke;

import duke.task.Task;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {

    @Test
    public void testGetTasks() {
        assertEquals(new ArrayList<Task>(), new TaskList().getTasks());
    }

    @Test
    public void testAddTask() {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("read book"));
        assertEquals(1, tasks.getSize());
        tasks.addTask(new ToDo("join CCA"));
        assertEquals(2, tasks.getSize());
    }

    @Test
    public void removeTask_validIndex_success() throws DukeException {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("read book"));
        tasks.removeTask(0);
        assertEquals(0, tasks.getSize());
    }

    @Test
    public void removeTask_invalidIndex_exceptionThrown() {
        try {
            TaskList tasks = new TaskList();
            tasks.removeTask(0);
            fail();
        } catch (DukeException e){
            assertEquals("OOPS!!! Index is out of bounds.", e.getMessage());
        }
    }

    @Test
    public void getTask_validIndex_success() throws DukeException{
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("read book"));
        Task task = tasks.getTask(0);
        assertEquals("[T][N] read book", task.toString());

    }

    @Test
    public void getTask_invalidIndex_exceptionThrown() {
        try {
            TaskList tasks = new TaskList();
            tasks.getTask(0);
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! Index is out of bounds.", e.getMessage());
        }

    }

    @Test
    public void testFind() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("read book"));
        tasks.add(new ToDo("join CCA"));
        tasks.add(new ToDo("borrow book"));
        TaskList tasklist = new TaskList(tasks);
        assertEquals(2, tasklist.find("book").size());
        assertEquals(1, tasklist.find("join").size());
        assertEquals(0, tasklist.find("sports").size());
    }
}
