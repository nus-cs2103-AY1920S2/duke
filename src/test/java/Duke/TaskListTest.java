package duke;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskListTest {
    @Test
    public void testTaskListAddTask() {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("Borrow book"));
        Assertions.assertEquals(tasks.get(0).toString(), "[T][" + "\u2718" + "] Borrow book");
    }

    @Test
    public void testTaskListDeleteTask() {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("Borrow book"));
        tasks.addTask(new Event("sister birthday", LocalDateTime.parse("2010-02-01T18:00")));
        tasks.deleteTask(1);
        Assertions.assertEquals(tasks.size(), 1);
    }
}
