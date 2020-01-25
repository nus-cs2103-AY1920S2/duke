import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    Todo todo = new Todo("sleep");

    @Test
    public void testTaskType() {
        assertEquals("T", todo.getTaskType());
    }

    @Test
    public void testNewTaskIsNotDone() {
        assertEquals("[✗]", todo.getStatusIcon());
    }

    @Test
    public void testDescription() {
        assertEquals("sleep", todo.getDescription());
    }
}
