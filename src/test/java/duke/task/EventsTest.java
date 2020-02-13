package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventsTest {

    Task task = new Event("go to school", LocalDate.of(2019, 2, 4));

    @Test
    public void markDoneTest() {
        assertFalse(task.isDone());
        task.markAsDone();
        assertTrue(task.isDone());
    }

    @Test
    public void getTaskNameTest() {
        assertEquals(task.getTaskName(), "go to school");
    }

    @Test
    public void getTaskTimeTest() {
        assertEquals(task.getTaskTime(), LocalDate.of(2019, 2, 4));
    }

}
