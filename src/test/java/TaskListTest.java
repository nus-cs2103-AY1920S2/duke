import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TaskListTest {
    @Test
    public void addTodo_sizeIncreases() {
        TaskList taskList = new TaskList();
        assertEquals(taskList.size(), 0);
        taskList.addTodo("homework");
        assertEquals(taskList.size(), 1);
    }

    @Test
    public void addDeadline_sizeIncreases() {
        TaskList taskList = new TaskList();
        assertEquals(taskList.size(), 0);
        taskList.addDeadline("submission /by 2019-12-15");
        assertEquals(taskList.size(), 1);
    }

    @Test
    public void addEvent_sizeIncreases() {
        TaskList taskList = new TaskList();
        assertEquals(taskList.size(), 0);
        taskList.addEvent("carnival /at 2020-11-13");
        assertEquals(taskList.size(), 1);
    }
}

