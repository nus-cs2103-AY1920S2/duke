import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void createNewTaskList_successful() {
        Task task1 = new Todo("description");
        Task task2 = new Deadline("description", "2020-01-01");
        Task task3 = new Event("description", "2020-12-31");

        List<Task> tasks = new ArrayList<Task>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        TaskList taskList = new TaskList(tasks);
        assertEquals(3, taskList.getTasksLength());
    }

    @Test
    public void deleteTask_successful() {
        Task task1 = new Todo("description");
        Task task2 = new Deadline("description", "2020-01-01");

        List<Task> tasks = new ArrayList<Task>();
        tasks.add(task1);
        tasks.add(task2);
        TaskList taskList = new TaskList(tasks);
        taskList.deleteTask(1);
        assertEquals(1, taskList.getTasksLength());
    }
}
