import duke.task.Event;
import duke.task.Task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testEventMarkAsDone() {
        Task testEventTask = new Event("Meeting", "Mon 2-4pm");
        assertEquals("[E][" + duke.task.Task.CROSS + "] " + "Meeting (at: Mon 2-4pm)", testEventTask.toString());
        testEventTask.markAsDone();
        assertEquals("[E][" + duke.task.Task.TICK + "] " + "Meeting (at: Mon 2-4pm)", testEventTask.toString());
    }
}