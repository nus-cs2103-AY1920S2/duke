package task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testTodo() {
        Task todo = new Todo("Task");
        String expectedText = "[T][\u2718] Task";
        String actualText = todo.toString();
        assertEquals(expectedText, actualText);
    }
}

