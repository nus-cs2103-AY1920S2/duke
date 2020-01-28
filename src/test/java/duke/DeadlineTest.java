package duke;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Task;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {


    private Task new_deadLine = new Deadline("deadline haha /by Monday 1800");
    @Test
    void format_task_tests() throws DukeException {


        // Checks exceptions. When there is only deadline but no date or description supplied to the
        // Task. Or when a ../by is not supplied for deadline.
        Exception exception = assertThrows(DukeException.class,()-> new_deadLine.format_tasks("deadline"));
        Exception exception1 = assertThrows(DukeException.class,()-> new_deadLine.format_tasks("deadline h"));
        Exception exception2 = assertThrows(DukeException.class,()-> new_deadLine.format_tasks("deadline hello_world /at Monday"));


        // Checks normal circumstances.
       assertEquals("Monday 1800", new_deadLine.format_tasks(new_deadLine.getDescription()));
       assertEquals("Monday", new_deadLine.format_tasks("deadline haha /by Monday"));
       assertEquals("2/12/2019", new_deadLine.format_tasks("deadline hehe /by 2/12/2019"));
       assertEquals("2/12/2019 1800", new_deadLine.format_tasks("deadline haha /by 2/12/2019 1800"));



    }



}
