package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Todo;

public class TodoTest {

    Todo todo = new Todo(false, "write an essay");

    @Test
    public void test_newTodo_success() {
        assertEquals(todo.toString(), "[T][✗] write an essay");
    }

    @Test
    public void test_markDone_success() {
        todo.markDone();
        assertEquals(todo.toString(), "[T][✓] write an essay");
    }
}