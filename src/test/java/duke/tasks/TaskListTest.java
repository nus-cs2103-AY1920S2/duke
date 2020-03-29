package duke.tasks;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    private TaskList taskList = new TaskList();

    @Test
    public void addTaskTest() {
        Task task = new Task("hello");
        TaskList.addTask(task, taskList);
        assertEquals(1, taskList.getTaskList().size());
    }
}
