import dukeclasses.ToDo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Tests the ToDo Class.
 */
public class ToDoTest {

    /**
     * Tests that the descriptions are correct.
     */
    @Test
    public void testToDoDescription() {
        ToDo task = new ToDo("testing");
        assertEquals("testing", task.getDescription());
    }
}
