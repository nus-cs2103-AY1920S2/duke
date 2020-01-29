import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest{
    @Test
    public void toStringTest() {
        ToDo todo = new ToDo("test");
        assertEquals("[T][N] test", todo.toString());
    }

    @Test
    public void fileStringTest() {
        ToDo todo = new ToDo("test");
        assertEquals("T | N | test", todo.fileString());
    }
}