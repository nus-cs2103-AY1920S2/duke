import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void markAsDoneTest() {
        Task todo = new Todo("todo1");
        todo.markAsDone();
        assertEquals("DONE",todo.getStatusIcon());
    }
}


