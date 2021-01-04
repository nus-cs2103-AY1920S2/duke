import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void getStatusInNumber() {
        Task t = new ToDo("read book");
        t.markAsDone();
        assertEquals("1", t.getStatusInNumber());
    }

    @Test
    public void format() {
        Task t = new ToDo("do project");
        assertEquals("T 0 do project", t.format());
    }

    @Test
    public void getStatusIcon() {
        //return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
        Task t = new ToDo("do project");
        assertEquals("\u2718", t.getStatusIcon());
    }
}
