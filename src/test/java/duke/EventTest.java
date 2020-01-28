package duke;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Task;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {


    private Task new_event = new Deadline("event haha /by Monday 1800");
    @Test
    void format_task_tests() throws DukeException {


        // Checks exceptions. When there is only deadline but no date or description supplied to the
        // Task. Or when a ../by is not supplied for deadline.
        Exception exception = assertThrows(DukeException.class,()-> new_event.format_tasks("deadline"));
        Exception exception1 = assertThrows(DukeException.class,()-> new_event.format_tasks("deadline h"));
        Exception exception2 = assertThrows(DukeException.class,()-> new_event.format_tasks("deadline hello_world /at Monday"));


        // Checks normal circumstances.
        assertEquals("Monday 1800", new_event.format_tasks(new_event.getDescription()));
        assertEquals("Monday", new_event.format_tasks("deadline haha /by Monday"));
        assertEquals("2/12/2019", new_event.format_tasks("deadline hehe /by 2/12/2019"));
        assertEquals("2/12/2019 1800", new_event.format_tasks("deadline haha /by 2/12/2019 1800"));



    }



}
