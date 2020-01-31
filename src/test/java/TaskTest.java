import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    Task task = new Task("read book");
    Task doneTask = new Task("return book");

    @Test
    public void getStatusIcon() {
        assertEquals("[N]", task.getStatusIcon());
        doneTask.markAsDone();
        assertEquals("[Y]", doneTask.getStatusIcon());
    }
}
