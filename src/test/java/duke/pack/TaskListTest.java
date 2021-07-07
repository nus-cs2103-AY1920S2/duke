package duke.pack;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void testGetSize() {
        assertEquals(0, new TaskList().getSize());
    }


}
