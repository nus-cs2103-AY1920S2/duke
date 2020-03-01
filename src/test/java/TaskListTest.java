import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.TaskList;

public class TaskListTest {

    @Test
    public void getSizeTest() {
        TaskList tasks = new TaskList();
        //Todo todo = new Todo("Mark papers");
        //tasks.addTask(todo);
        assertEquals(0,tasks.getSize());
    }
}
