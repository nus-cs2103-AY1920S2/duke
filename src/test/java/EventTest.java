import duke.Event;
import duke.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void getMnemonic() {
        Task task = new Event("", null, false);
        assertEquals("E", task.getMnemonic());
    }

    @Test
    public void getName() {
        Task task = new Event("abc", null, false);
        assertEquals("abc", task.getName());
    }
}
