import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void testGetRecord() {
        assertEquals(new TaskList().getRecord(), new TaskList().getRecord());
    }

    @Test
    public void testAddToDo() {
        TaskList list = new TaskList();
        list.addToDo("Testing");
        assertEquals(1, list.getRecord().size());
    }
}
