import duke.task.ToDo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for ToDo.java.
 */
public class ToDoTest {
    private ToDo todo;

    /**
     * Creates a new ToDo task for testing.
     */
    public ToDoTest() {
        this.todo = new ToDo("test todo");
    }

    @Test
    public void getType() {
        assertEquals(this.todo.getType(), "T");
    }

    @Test
    public void getDescription() {
        assertEquals(this.todo.getDescription(), "test todo");
    }

    @Test
    public void isDone() {
        assertEquals(this.todo.isDone(), false);
        this.todo.setDone();
        assertEquals(this.todo.isDone(), true);
    }
}