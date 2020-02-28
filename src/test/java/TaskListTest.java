import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    TaskList taskList = new TaskList();

    @Test
    public void size() {
        taskList.addTask(new ToDo("di hoc"));
        assertEquals(1, taskList.getSize());
    }

    @Test
    public void isEmpty() {
        assertEquals(0, taskList.getSize());
    }
}
