import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.time.LocalDateTime;
import java.util.ArrayList;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;


public class TaskListTest {

    @Test
    public void testCorrectTask() {
        TaskList tasks = new TaskList(new ArrayList<Task>());
        tasks.addTask(new Todo("play game"));
        tasks.addTask(new Deadline("study", LocalDateTime.parse("2010-02-01T18:00")));
        tasks.addTask(new Event("holiday", LocalDateTime.parse("2039-10-03T21:00")));

        Assertions.assertEquals(tasks.getTask(2).toString(), "[E][x] holiday (at: Oct 3 2039, 9:00 PM)");
    }

    @Test
    public void testNumTasks() {
        TaskList tasks = new TaskList(new ArrayList<Task>());
        tasks.addTask(new Todo("play game"));
        tasks.addTask(new Deadline("study", LocalDateTime.parse("2010-02-01T18:00")));
        tasks.addTask(new Event("holiday", LocalDateTime.parse("2039-10-03T21:00")));

        Assertions.assertEquals(3, tasks.getTaskListSize());
    }

    @Test
    public void testTaskAddingMethod() {
        TaskList tasks = new TaskList(new ArrayList<Task>());
        tasks.addNewTodo("play game");
        tasks.addNewDeadline("study", LocalDateTime.parse("2010-02-01T18:00"));
        tasks.addNewEvent("holiday", LocalDateTime.parse("2039-10-03T21:00"));

        Assertions.assertEquals(tasks.getTaskListSize(), 3);
    }
}
