import duke.task.DateTask;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void testTaskType() {
        Task newTodo = new ToDo("greet world");
        assertEquals("T", newTodo.getType());

        LocalDate inputDate = LocalDate.parse("2020-02-21");
        Task newEvent = new Event("watch a movie", inputDate);
        assertEquals("E", newEvent.getType());

        Task newDeadline = new Deadline("submit plants to lab", inputDate);
        assertEquals("D", newDeadline.getType());
    }

    @Test
    public void testDateTask_stringFormat() {
        LocalDate inputDate = LocalDate.parse("2020-02-21");
        DateTask newEvent = new Event("watch a movie", inputDate);
        assertEquals("[E][X]watch a movie (at: Feb 21 2020)", newEvent.toString());

        DateTask newDeadline = new Deadline("submit plants to lab", inputDate);
        assertEquals("[D][X]submit plants to lab (by: Feb 21 2020)", newDeadline.toString());
    }
}


