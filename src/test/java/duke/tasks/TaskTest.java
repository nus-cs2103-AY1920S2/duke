package duke.tasks;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskTest {

    LocalDate testDate = LocalDate.parse("2020-02-09");
    @Test
    public void testEventConstructor() {
        Event task = new Event("1", "blah", "9/02/2020");
        assertEquals(TaskType.EVENT, task.getTaskType());
        assertEquals("blah", task.getDescription());
        assertEquals(testDate, task.getTaskTime());
    }

    @Test
    public void testDeadlineConstructor() {
        Deadline task = new Deadline("1", "blah", "9/02/2020");
        assertEquals(TaskType.DEADLINE, task.getTaskType());
        assertEquals("blah", task.getDescription());
        assertEquals(testDate, task.getTaskTime());
    }

    @Test
    public void testToDoConstructor() {
        ToDo task = new ToDo("0", "blah");
        assertEquals(TaskType.TODO, task.getTaskType());
        assertEquals("blah", task.getDescription());
    }


}
