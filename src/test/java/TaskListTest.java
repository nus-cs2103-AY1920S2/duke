import duke.commands.TaskList;
import duke.tasks.ToDo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void addAndSizeTest() {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("borrow book"), "");
        taskList.add(new ToDo("return book"), "");
        assertEquals(2, taskList.size());
    }

    @Test
    public void doneAndDeleteAndGetTest() {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("borrow book"), "");
        taskList.add(new ToDo("return book"), "");
        taskList.delete(0);
        taskList.done(0);
        assertEquals("[T][Y] return book", taskList.get(0).toString());
    }
}
