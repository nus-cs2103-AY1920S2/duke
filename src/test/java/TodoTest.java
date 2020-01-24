import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testTaskType() {
        assertEquals("T", new Todo("sleep").getTaskType());
    }

    @Test
    public void testNewTaskIsNotDone() {
        assertEquals("[✗]", new Todo("sleep").getStatusIcon());
    }
}
