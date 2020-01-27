package duke;

import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
 
class TaskListTest {
    @Test
    void get_validId_success() throws Exception {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("read book"));
        assertEquals("[T][N] read book", tasks.get(1).toString());
    }

    @Test
    void get_invalidId_exceptionThrown() {
        try {
            TaskList tasks = new TaskList();
            tasks.add(new Todo("read book"));
            tasks.get(2);
            fail();
        } catch (DukeException e) {
            assertEquals("Task cannot be found.", e.getMessage());
        }
    }
    
    @Test
    void add() throws Exception {
        TaskList tasks = new TaskList();
        assertTrue(tasks.isEmpty());
        tasks.add(new Todo("read book"));
        assertEquals(1, tasks.size());
    }

    @Test
    void remove_validId_success() throws Exception {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("read book"));
        tasks.remove(1);
        assertEquals(0, tasks.size());
    }

    @Test
    void remove_invalidId_exceptionThrown() {
        try {
            TaskList tasks = new TaskList();
            tasks.add(new Todo("read book"));
            tasks.remove(2);
            fail();
        } catch (DukeException e) {
            assertEquals("Task cannot be found.", e.getMessage());
        }
    }

    @Test
    void getTasks() throws Exception {
        TaskList tasks = new TaskList();
        assertEquals(new ArrayList<Task>(), tasks.getTasks());
    }
}
