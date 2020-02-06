package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testMarkAsDone() {
        Task testTodo = new Todo("test todo");
        assertEquals(false, testTodo.isDone());
        testTodo.markAsDone();
        assertEquals(true, testTodo.isDone());
    }

    @Test
    public void testGetStatusIcon() {
        Task testTodo = new Todo("test todo");
        assertEquals("N", testTodo.getStatusIcon());
        testTodo.markAsDone();
        assertEquals("Y", testTodo.getStatusIcon());
    }
}
