import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {
    @Test
    public void AddTaskToTaskListTest(){
        TaskList tasks = new TaskList();
        Task task = new Task("Dummy");

        tasks.addTask(task);
        assertTrue(tasks.getAllTasks().contains(task));
        assertEquals(tasks.getCount(), 1);
    }

    @Test
    public void getTaskCounterTest(){
        TaskList tasks = new TaskList();
        Task task = new Task("Dummy");

        assertEquals(tasks.getCount(), 0);
        tasks.addTask(task);
        assertEquals(tasks.getCount(), 1);
    }
}