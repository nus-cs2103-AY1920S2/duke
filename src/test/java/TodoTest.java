import duke.commons.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void constructorTest() {
        Todo todo = new Todo("todo", false, "Revise CS3230");
        assertEquals(todo.toString(), "[ T ] [ ✗ ] Revise CS3230");
    }
}
