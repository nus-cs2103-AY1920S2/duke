import Togepi.Tasks.Task;
import Togepi.Tasks.TaskList;
import Togepi.Tasks.Todo;
import Togepi.Task;
import Togepi.TaskList;
import Togepi.Todo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void markCompleteTest() {
        ArrayList<Task> tasklist = new ArrayList<>();
        tasklist.add(new Todo("test 1"));
        tasklist.add(new Todo("test 2"));
        tasklist.add(new Todo("test 3"));
        TaskList tasks = new TaskList(tasklist, 3);
        tasks.markComplete("done 2");
        String finished = String.valueOf(tasklist.get(1));
        assertEquals("[T][✓] test 2", finished);
    }

    @Test
    public void deleteTaskTest() {
        ArrayList<Task> tasklist = new ArrayList<>();
        tasklist.add(new Todo("test 1"));
        tasklist.add(new Todo("test 2"));
        tasklist.add(new Todo("test 3"));
        TaskList tasks = new TaskList(tasklist, 3);
        String deleted = String.valueOf(tasklist.get(1));
        tasks.deleteTask("delete 2");
        assertEquals("[T][✘] test 2", deleted);
    }
}
