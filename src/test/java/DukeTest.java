import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void TodoTest() {
        Todo newTask = new Todo("sleep");
        newTask.setDone();
        assertEquals(1, newTask.getDoneInt());
    }

    @Test
    public void EventTest() {
        Event newTask = new Event("meeting", "Sunday 1pm");
        newTask.setDone();
        assertEquals("E - 1 - meeting - Sunday 1pm" , newTask.updateFile());
    }

    @Test
    public void DeadlineTest() throws DukeException {
        Deadline newTask = new Deadline("Assignment 1", "2019-01-01", "1000");
        assertEquals("2019-01-01 1000" , newTask.assembleDeadlineDateAndTime());
    }
}
