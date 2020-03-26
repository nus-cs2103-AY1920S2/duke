import duke.tasks.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testStringConversion() {
        assertEquals("[T] [ ] nom", new Todo("nom").toString());
    }
    
    @Test
    public void testFormatConversion() {
        assertEquals("T | 0 | nom", new Todo("nom").format());
    }
    
    @Test
    public void testMarkAsCompleted() {
        Todo newTodo = new Todo("nom");
        newTodo.markAsCompleted();
        assertEquals("T | 1 | nom", newTodo.format());
    }
}
