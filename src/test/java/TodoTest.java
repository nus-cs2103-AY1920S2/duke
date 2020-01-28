import duke.tasks.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void dummyTest() {
        assertEquals(2,2);
    }
    @Test
    public void testTodoText() {
        Todo t = new Todo("todo hack the pentagon");
        assertEquals("[T][✗]hack the pentagon", t.toString());
    }
}
