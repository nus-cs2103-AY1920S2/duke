import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void testTodo() throws Throwable {

        Todo todo = new Todo("testing", 1);

        String status = todo.getStatusIcon();

        assertEquals("N",status);
    }
}