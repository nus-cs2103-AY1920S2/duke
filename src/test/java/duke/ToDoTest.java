package duke;

import duke.task.ToDos;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The Class ToDoTest.
 */
public class ToDoTest {

    /**
     * Test to do description.
     */
    @Test
    public void testToDoDescription() {
        ToDos task = new ToDos("buy food for mum");
        assertEquals("buy food for mum", task.getDescription());
    }
}
