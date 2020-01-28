import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testOne() {
        ToDo todo = new ToDo("bro");
        assertEquals(todo.toString(), "[T] [" + "✘" + "] bro");
    }
}