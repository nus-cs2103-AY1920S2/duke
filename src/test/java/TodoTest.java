import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TodoTest {

    @Test
    public void dummyTest() {
        Todo todo = new Todo(1, "test");
        assertEquals(todo.toString(), "[T][Y] test");
    }
}

