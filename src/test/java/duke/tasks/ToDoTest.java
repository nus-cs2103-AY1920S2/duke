package duke.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    protected ToDo testToDo = new ToDo("TestTask");

    @Test
    public void testGetType() {
        assertEquals("[T]", testToDo.getType());
    }

    @Test
    public void testEquals() {
        assertEquals(true, testToDo.equals(new ToDo("TestTask")));
    }
}
