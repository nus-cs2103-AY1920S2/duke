package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TodoTaskTest {
    @Test
    public void testToString() {
        TodoTask task;

        task = new TodoTask("eat lunch");
        assertEquals("[T][ ] eat lunch", task.toString());

        task = new TodoTask("eat lunch");
        task.markAsCompleted();
        assertEquals("[T][X] eat lunch", task.toString());

        task = new TodoTask("eat lunch", true);
        assertEquals("[T][X] eat lunch", task.toString());

        task = new TodoTask("eat lunch", false);
        assertEquals("[T][ ] eat lunch", task.toString());
    }

    @Test
    public void testToStringDelimited() {
        TodoTask task;

        task = new TodoTask("eat lunch");
        assertEquals("T |   | eat lunch", task.toStringDelimited());

        task = new TodoTask("eat lunch");
        task.markAsCompleted();
        assertEquals("T | X | eat lunch", task.toStringDelimited());
    }
}
