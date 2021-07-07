package duke;

import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void creationTest() {
        Todo todo = new Todo("Hello World!");
        assertEquals(todo.toString(), "[T][✗] Hello World!");
    }

    @Test
    public void saveToStringTest() {
        Todo todo = new Todo("Hello World!");
        assertEquals(todo.toSaveString(), "0 || todo || Hello World!");
    }

    @Test
    public void saveToStringDoneTest() {
        Todo todo = new Todo("Hello World!");
        todo.done();
        assertEquals(todo.toSaveString(), "1 || todo || Hello World!");
    }
}
