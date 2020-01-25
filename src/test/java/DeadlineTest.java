import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    Deadline deadline = new Deadline("cs3243 tutorial", "2020-01-31 17:00");

    @Test
    public void testTaskType() {
        assertEquals("D", deadline.getTaskType());
    }

    @Test
    public void testNewTaskIsNotDone() {
        assertEquals("[✗]", deadline.getStatusIcon());
    }

    @Test
    public void testDescription() {
        assertEquals("cs3243 tutorial", deadline.getDescription());
    }
}
