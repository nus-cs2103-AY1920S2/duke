package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
 
class TodoTest {
    @Test
    void constructorTest1() throws Exception {
        Todo todo = new Todo("read book");
        assertEquals("read book", todo.description);
    }

    @Test
    void constructorTest2() throws Exception {
        Todo todo = new Todo("read book", true);
        assertEquals("read book", todo.description);
        assertTrue(todo.isDone);
    }

    @Test
    void formatToSave() throws Exception {
        Todo todo = new Todo("read book");
        assertEquals("T | 0 | read book", todo.formatToSave());
    }

    @Test
    void stringConversion() throws Exception {
        Todo todo = new Todo("read book");
        assertEquals("[T][N] read book", todo.toString());
    }
}
