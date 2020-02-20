import duke.tasks.ToDo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    /**
     * test that output string is correct.
     */
    @Test
    public void toStringTest() {
        ToDo todo = new ToDo("test");
        assertEquals("[T][\u2718] test", todo.toString());
    }

    /**
     * test that file data string is correct.
     */
    @Test
    public void fileStringTest() {
        ToDo todo = new ToDo("test");
        assertEquals("T | N | test", todo.fileString());
    }
}
