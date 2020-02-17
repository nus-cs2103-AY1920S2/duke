package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTodo {
    @Test
    public void testSerialize() {
        assertEquals("T,0,read books,", new Todo("read books").serialize());

        Todo todo = new Todo("borrow books");
        todo.markAsDone();
        assertEquals("T,1,borrow books,", todo.serialize());
    }

    @Test
    public void testToString() {
        assertEquals("[T][✘] read books", new Todo("read books").toString());

        Todo todo = new Todo("borrow books");
        todo.markAsDone();
        assertEquals("[T][✔] borrow books", todo.toString());
    }
}
