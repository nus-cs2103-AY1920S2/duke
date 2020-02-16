package umaikaze.duke.task;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TaskTest {
    @Test
    public void task_normalInput() {
        Task t = new Task("Test Description");
        assertEquals(t.toString(), "[\u2718] Test Description");
    }

    @Test
    public void markDone_normalInput() {
        Task t = new Task("Test Description");
        t.markDone();
        assertEquals(t.toString(), "[\u2713] Test Description");
    }
}