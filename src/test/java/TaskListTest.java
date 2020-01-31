import duke.TaskList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void testSize() {
        TaskList list = new TaskList();
        assertEquals(0, list.size());
    }
}