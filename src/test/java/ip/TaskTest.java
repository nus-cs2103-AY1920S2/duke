package ip;

import ip.task.Deadline;
import ip.task.Event;
import ip.task.Todo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void testTodoToString() {
        String name = "create tests";
        Assertions.assertEquals(new Todo(name).toString(), "[T][x] " + name);
    }
    @Test
    public void testDeadlineToString() {
        String name = "create tests";
        String by = "yesterday";
        Assertions.assertEquals(new Deadline(name, by).toString(), "[D][x] " + name + " (by: " + by + ")");
    }
    @Test
    public void testEventToString() {
        String name = "create tests";
        String at = "next week";
        Assertions.assertEquals(new Event(name, at).toString(), "[E][x] " + name + " (at: " + at + ")");
    }
}
