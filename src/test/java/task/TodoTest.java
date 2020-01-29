package task;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TodoTest {
    @Test
    public void testTodo() {
        Task todo = new Todo("Task");
        String expectedText = "[T][N] Task";
        String actualText = todo.toString();
        assertEquals(expectedText, actualText);
        String expectedText2 = "T/N/Task";
        String actualText2 = todo.toStringTxt();
        assertEquals(expectedText2, actualText2);
    }
}
