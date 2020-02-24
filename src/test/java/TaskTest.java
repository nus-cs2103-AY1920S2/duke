import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TaskTest {

    @Test
    public void dummyTest() {
        Task task = new Task("todo",0, "read book");
        assertEquals(task.getType(), "todo");
    }
}