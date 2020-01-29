import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void size() {
        Task task = new Todo("do tutorials");
        TaskList tasks = new TaskList(new ArrayList<Task>());
        tasks.add(task);
        assertEquals(1, TaskList.size());
    }

    public void delete() {
        Task task = new Todo("do tutorials");
        TaskList tasks = new TaskList(new ArrayList<Task>());
        tasks.add(task);
        tasks.delete(1);
        assertEquals(0, TaskList.size());
    }
}