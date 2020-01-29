package task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import exception.DukeException;

public class TaskTest {
    @Test
    public void taskCreation() {
        try {
            Task todo = Task.newTask("todo done with j-unit");
            assertEquals("[T][N] done with j-unit", todo.toString());
            Task deadline = Task.newTask("deadline return book /by 19-12-2019 4pm");
            assertEquals("[D][N] return book (by: 19 Dec 2019 16:00)", deadline.toString());
            Task event = Task.newTask("event project meeting /at 02/11/2020 2359");
            assertEquals("[E][N] project meeting (at: 02 Nov 2020 23:59)", event.toString());
        } catch (DukeException err ) {
        }
    }

    @Test
    public void taskMarking() {
        try {
            Task event = Task.newTask("event project meeting /at 02/11/2020 2359");
            assertEquals(false, event.isDone());
            event.setDone();
            assertEquals(true, event.isDone());
        }catch (DukeException err ) {
        }
    }
}
