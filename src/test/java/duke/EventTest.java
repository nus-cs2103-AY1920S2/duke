package duke;

import duke.task.Deadline;
import duke.task.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;


class EventTest {


    private Task newEvent = new Deadline("event haha /by Monday 1800");

    @Test
    void format_task_tests() throws DukeException {


        // Checks exceptions. When there is only deadline but no date or description supplied to the
        // Task. Or when a ../by is not supplied for deadline.
        Exception exception = assertThrows(DukeException.class, () -> newEvent.formatTasks("deadline"));
        Exception exception1 = assertThrows(DukeException.class, () -> newEvent.formatTasks("deadline h"));
        Exception exception2 = assertThrows(DukeException.class, () -> newEvent
                .formatTasks("deadline hello_world /at Monday"));


        // Checks normal circumstances.
        assertEquals("Monday 1800", newEvent.formatTasks(newEvent.getDescription()));
        assertEquals("Monday", newEvent.formatTasks("deadline haha /by Monday"));
        assertEquals("2/12/2019", newEvent.formatTasks("deadline hehe /by 2/12/2019"));
        assertEquals("2/12/2019 1800", newEvent.formatTasks("deadline haha /by 2/12/2019 1800"));


    }


}
