import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testTaskType() {
        assertEquals("E", new Event("dinner","2020-02-05 19:00").getTaskType());
    }

    @Test
    public void testNewTaskIsNotDone() {
        assertEquals("[✗]", new Event("dinner", "2020-02-05 19:00").getStatusIcon());
    }
}
