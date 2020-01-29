import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    ToDo todo;

    public ToDoTest() {
        this.todo = new ToDo("test todo");
    }

    @Test
    public void testGetType() {
        assertEquals(this.todo.getType(), "T");
    }

    @Test
    public void testGetDescription() {
        assertEquals(this.todo.getDescription(), "test todo");
    }

    @Test
    public void testIsDone() {
        assertEquals(this.todo.isDone(), false);
        this.todo.setDone();
        assertEquals(this.todo.isDone(), true);
    }
}