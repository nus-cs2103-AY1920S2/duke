import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class TaskListTest {

    @Test
    public void testCorrectTask() {
        TaskList tasks = new TaskList(new ArrayList<Task>());
        tasks.add(new Todo("play game"));
        tasks.add(new Deadline("study", LocalDateTime.parse("2010-02-01T18:00")));
        tasks.add(new Event("holiday", LocalDateTime.parse("2039-10-03T21:00")));

        Assertions.assertEquals(tasks.getTask(2).toString(), "[E][x] holiday (at: Oct 3 2039, 9:00 PM)");
    }

    @Test
    public void testNumTasks() {
        TaskList tasks = new TaskList(new ArrayList<Task>());
        tasks.add(new Todo("play game"));
        tasks.add(new Deadline("study", LocalDateTime.parse("2010-02-01T18:00")));
        tasks.add(new Event("holiday", LocalDateTime.parse("2039-10-03T21:00")));

        Assertions.assertEquals(3, tasks.getSize());
    }

    @Test
    public void testTaskAddingMethod() {
        TaskList tasks = new TaskList(new ArrayList<Task>());
        tasks.newTodo("play game");
        tasks.newDeadline("study", LocalDateTime.parse("2010-02-01T18:00"));
        tasks.newEvent("holiday", LocalDateTime.parse("2039-10-03T21:00"));

        Assertions.assertEquals(tasks.getSize(), 3);
    }
}
