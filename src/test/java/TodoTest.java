import duke.tasks.Task;
import duke.tasks.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void todoTest() {
        Task task = new Todo("test");
        String output = "[T][✗] test";
        assertEquals(output, task.toString());
        String output1 = "T | 0 | test\n";
        assertEquals(output1, task.formatText());
    }
}