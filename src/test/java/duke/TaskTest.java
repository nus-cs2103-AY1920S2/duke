package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testTaskDone() {
        Task task = new Task("buy book");
        assertEquals(false, task.isDone());
        task.done();
        assertEquals(true, task.isDone());
    }

    @Test
    public void testTaskStringConversion(){
        Task task = new Task("read book");
        assertEquals("[INCOMPLETE] read book", task.toString());
        task.done();
        assertEquals("[DONE] read book", task.toString());
    }
}
