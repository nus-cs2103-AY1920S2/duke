import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void testMarkAsDone() {
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.arr.add(new Todo("read book"));
        taskList.markAsDone(1);
        assertEquals(true, taskList.arr.get(0).done);
    }

    @Test
    public void testDeleteTask() {
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.arr.add(new Todo("read book"));
        taskList.deleteTask(1);
        assertEquals(0, taskList.arr.size());
    }
}
