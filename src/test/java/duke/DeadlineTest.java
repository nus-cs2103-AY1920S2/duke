package duke;

import duke.task.Deadline;
import duke.task.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeadlineTest {


    private Task newDeadLine = new Deadline("deadline haha /by Monday 1800");

    @Test
    void format_task_tests() throws DukeException {


        // Checks exceptions. When there is only deadline but no date or description supplied to the
        // Task. Or when a ../by is not supplied for deadline.
        Exception exception = assertThrows(DukeException.class, () -> newDeadLine.formatTasks("deadline"));
        Exception exception1 = assertThrows(DukeException.class, () -> newDeadLine.formatTasks("deadline h"));
        Exception exception2 = assertThrows(DukeException.class, () -> newDeadLine
                .formatTasks("deadline hello_world /at Monday"));


        // Checks normal circumstances.
        assertEquals("Monday 1800", newDeadLine.formatTasks(newDeadLine.getDescription()));
        assertEquals("Monday", newDeadLine.formatTasks("deadline haha /by Monday"));
        assertEquals("2/12/2019", newDeadLine.formatTasks("deadline hehe /by 2/12/2019"));
        assertEquals("2/12/2019 1800", newDeadLine.formatTasks("deadline haha /by 2/12/2019 1800"));


    }


}
