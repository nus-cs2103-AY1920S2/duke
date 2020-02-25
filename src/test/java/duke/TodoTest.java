package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testTodoStringConversion(){
        Todo todo = new Todo(" read book");
        assertEquals("[T][INCOMPLETE] read book", todo.toString());
        todo.done();
        assertEquals("[T][DONE] read book", todo.toString());
    }
}
