package duke;

import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TodoTest {

    @Test
    void generateSaveFileEntry_validTodo_returnsEntry() {
        assertEquals("T | 0 | read book\n", new Todo("read book").generateSaveFileEntry());
        assertEquals("T | 1 | read book\n", new Todo("read book", true).generateSaveFileEntry());
        assertEquals("T | 0 | read book\n", new Todo("read book", false).generateSaveFileEntry());
    }

    @Test
    void toString_validTodo_returnsString() {
        assertEquals("[T] [X] read book", new Todo("read book").toString());
        assertEquals("[T] [V] read book", new Todo("read book", true).toString());
    }
}