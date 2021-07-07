package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testTask() {
        ToDo toDo = new ToDo("newTask");
        assertEquals("newTask", toDo.description);
    }
}
