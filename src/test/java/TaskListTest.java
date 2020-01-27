import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void getTaskListSizeTest() {
        ArrayList<Task> t = new ArrayList<>();
        t.add(new ToDo("Test1"));
        t.add(new ToDo("Test2"));
        t.add(new ToDo("Test3"));
        TaskList list = new TaskList(t);
        assertEquals(list.getSize(), 3);
    }

    @Test
    public void deleteTaskTest() {
        ArrayList<Task> t = new ArrayList<>();
        t.add(new ToDo("Test1"));
        t.add(new ToDo("Test2"));
        t.add(new ToDo("Test3"));
        t.add(new ToDo("Test4"));
        t.add(new ToDo("Test5"));
        TaskList list = new TaskList(t);
        Task deleted = list.deleteTask(3);
        assertEquals("Test3", deleted.getTaskname());
    }

    @Test
    public void getTaskTest() {
        ArrayList<Task> t = new ArrayList<>();
        t.add(new ToDo("Test1"));
        t.add(new ToDo("Test2"));
        t.add(new ToDo("Test3"));
        t.add(new ToDo("Test4"));
        t.add(new ToDo("Test5"));
        TaskList list = new TaskList(t);
        Task retrieved = list.getTask(4);
        assertEquals("Test4", retrieved.getTaskname());
    }

}
