import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    Event event = new Event("dinner","2020-02-05 19:00");

    @Test
    public void testTaskType() {
        assertEquals("E", event.getTaskType());
    }

    @Test
    public void testNewTaskIsNotDone() {
        assertEquals("[✗]", event.getStatusIcon());
    }

    @Test
    public void testDescription() {
        assertEquals("dinner", event.getDescription());
    }
}
