package duke;

import duke.Task;
import duke.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void addTodoTest() {
        Todo todo = new Todo("read book");
        assertEquals("[T][\u2718] read book", todo.toString());
    }

    @Test
    public void doneTodoTest() {
        Todo todo = new Todo("read book");
        todo.markAsDone();
        assertEquals("[T][\u2713] read book", todo.toString());
    }
}