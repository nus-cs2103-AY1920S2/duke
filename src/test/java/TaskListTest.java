import duke.logic.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for TaskList.java.
 */
public class TaskListTest {
    private TaskList tasks;
    private Task todo;
    private Task deadline;
    private Task event;

    /**
     * Creates a new TaskList as well as all different tasks (i.e. ToDo, Deadline, Event) for testing.
     */
    public TaskListTest() {
        this.tasks = new TaskList();
        this.todo = new ToDo("test todo");
        this.deadline = new Deadline("test deadline", LocalDate.parse("2020-10-21"));
        this.event = new Event("test event", LocalDate.parse("2020-08-15"));
    }

    @Test
    public void addTask() {
        this.tasks.addTask(this.todo);
        this.tasks.addTask(this.deadline);
        this.tasks.addTask(this.event);
        ArrayList<Task> arrTasks = this.tasks.getTasks();
        assertEquals(arrTasks.get(0) instanceof ToDo, true);
        assertEquals(arrTasks.get(0).getDescription(), "test todo");
        assertEquals(arrTasks.get(1) instanceof Deadline, true);
        assertEquals(arrTasks.get(1).getDescription(), "test deadline");
        assertEquals(arrTasks.get(2) instanceof Event, true);
        assertEquals(arrTasks.get(2).getDescription(), "test event");
    }

    @Test
    public void markTaskAsDone() {
        this.tasks.addTask(this.todo);
        this.tasks.addTask(this.deadline);
        this.tasks.addTask(this.event);
        assertEquals(this.todo.isDone(), false);
        assertEquals(this.deadline.isDone(), false);
        assertEquals(this.event.isDone(), false);
        Task t;
        t = this.tasks.markTaskAsDone(0);
        assertEquals(this.tasks.getTasks().get(0).isDone(), true);
        assertEquals(t, this.todo);
        t = this.tasks.markTaskAsDone(1);
        assertEquals(this.tasks.getTasks().get(1).isDone(), true);
        assertEquals(t, this.deadline);
        t = this.tasks.markTaskAsDone(2);
        assertEquals(this.tasks.getTasks().get(2).isDone(), true);
        assertEquals(t, this.event);
    }

    @Test
    public void removeTask() {
        this.tasks.addTask(this.todo);
        this.tasks.addTask(this.deadline);
        this.tasks.addTask(this.event);
        Task t;
        assertEquals(this.tasks.getNumTasks(), 3);
        t = this.tasks.removeTask(0);
        assertEquals(this.tasks.getNumTasks(), 2);
        assertEquals(t, this.todo);
        t = this.tasks.removeTask(0);
        assertEquals(this.tasks.getNumTasks(), 1);
        assertEquals(t, this.deadline);
        t = this.tasks.removeTask(0);
        assertEquals(this.tasks.getNumTasks(), 0);
        assertEquals(t, this.event);
    }
}
