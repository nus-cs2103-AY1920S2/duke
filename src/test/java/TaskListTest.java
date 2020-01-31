import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    private TaskList tasks;
    private Task todo, deadline, event;
    private Task t;

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
        assertEquals(this.todo, arrTasks.get(0));
        assertEquals(this.deadline, arrTasks.get(1));
        assertEquals(this.event, arrTasks.get(2));
    }

    @Test
    public void markTaskAsDone() {
        this.tasks.addTask(this.todo);
        this.tasks.addTask(this.deadline);
        this.tasks.addTask(this.event);
        assertEquals(this.todo.isDone(), false);
        assertEquals(this.deadline.isDone(), false);
        assertEquals(this.event.isDone(), false);
        this.t = this.tasks.markTaskAsDone(0);
        assertEquals(this.tasks.getTasks().get(0).isDone(), true);
        assertEquals(this.t, this.todo);
        this.t = this.tasks.markTaskAsDone(1);
        assertEquals(this.tasks.getTasks().get(1).isDone(), true);
        assertEquals(this.t, this.deadline);
        this.t = this.tasks.markTaskAsDone(2);
        assertEquals(this.tasks.getTasks().get(2).isDone(), true);
        assertEquals(this.t, this.event);
    }

    @Test
    public void removeTask() {
        this.tasks.addTask(this.todo);
        this.tasks.addTask(this.deadline);
        this.tasks.addTask(this.event);
        assertEquals(this.tasks.getNumTasks(), 3);
        this.t = this.tasks.removeTask(0);
        assertEquals(this.tasks.getNumTasks(), 2);
        assertEquals(this.t, this.todo);
        this.t = this.tasks.removeTask(0);
        assertEquals(this.tasks.getNumTasks(), 1);
        assertEquals(this.t, this.deadline);
        this.t = this.tasks.removeTask(0);
        assertEquals(this.tasks.getNumTasks(), 0);
        assertEquals(this.t, this.event);
    }
}
