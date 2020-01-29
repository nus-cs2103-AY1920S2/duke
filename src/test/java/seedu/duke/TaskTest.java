package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testMarkAsDone() {
        Task testTodo = new Todo("test todo");
        assertEquals(false, testTodo.getDoneStatus());
        testTodo.markAsDone();
        assertEquals(true, testTodo.getDoneStatus());
    }

    @Test
    public void testGetStatusIcon() {
        Task testTodo = new Todo("test todo");
        assertEquals("N", testTodo.getStatusIcon());
        testTodo.markAsDone();
        assertEquals("Y", testTodo.getStatusIcon());
    }

    @Test
    public void testGetType() {
        Task testTodo = new Todo("test todo");
        assertEquals("T", testTodo.getType());
    }
}
