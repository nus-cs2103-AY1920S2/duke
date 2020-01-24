import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testTaskType() {
        assertEquals("D", new Deadline("cs3243 tutorial", "2020-01-31 17:00").getTaskType());
    }

    @Test
    public void testNewTaskIsNotDone() {
        assertEquals("[✗]", new Deadline("cs3243 tutorial", "2020-01-31 17:00").getStatusIcon());
    }
}
