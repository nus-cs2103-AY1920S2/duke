import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.TaskList;
import duke.Todo;
import duke.Task;

public class TaskListTest {

    //@Test
   // public void getTaskTest() {
        TaskList tasks = new TaskList();
        //Todo todo = new Todo("Mark papers");
        //tasks.addTask(todo);
        //assertEquals(todo, tasks.getTask(0));
    //}

    @Test
    public void getSizeTest() {
        TaskList tasks = new TaskList();
        //Todo todo = new Todo("Mark papers");
        //tasks.addTask(todo);
        assertEquals(0,tasks.getSize());
    }
}
