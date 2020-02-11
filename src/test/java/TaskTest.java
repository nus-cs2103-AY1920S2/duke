import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testTaskDone() {
        Task t = new Task("todo run");
        assertEquals(0, t.getIsTaskDone());
    }

    @Test
    public void testDescription() {
        Task t = new Task("todo work");
        assertEquals("todo work", t.getDescription());
    }
}
