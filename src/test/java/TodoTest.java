import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testTodoStringConversion(){
        Todo todo = new Todo("borrow book");
        assertEquals("[T][✘]borrow book", todo.toString());
        todo.markAsDone();
        assertEquals("[T][✓]borrow book", todo.toString());
    }
}