package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class TaskListTest {

    TaskList taskList = new TaskList(new ArrayList<Task>());

    @Test
    public void addTask() {
        taskList.addToDo("test");
        assertEquals(1, taskList.tasks.size());
        assertEquals(false, taskList.tasks.get(0).isDone);
        taskList.addDeadline("test deadline",  LocalDate.parse("2020-12-12"));
        assertEquals(2, taskList.tasks.size());
        assertEquals("test deadline", taskList.tasks.get(1).description);
        taskList.addEvent("test event", LocalDate.parse("2020-10-10"));
        assertEquals(3, taskList.tasks.size());
        assertEquals("Oct 10 2020", ((Event)(taskList.tasks.get(2))).at.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Test
    public void deleteTaskNotExist() {
        taskList.addToDo("test");
        assertEquals(1, taskList.tasks.size());
        assertEquals(false, taskList.tasks.get(0).isDone);
        taskList.addDeadline("test deadline",  LocalDate.parse("2020-12-12"));
        assertEquals(2, taskList.tasks.size());
        assertEquals("test deadline", taskList.tasks.get(1).description);
        taskList.addEvent("test event", LocalDate.parse("2020-10-10"));
        assertEquals(3, taskList.tasks.size());
        assertEquals("Oct 10 2020", ((Event)(taskList.tasks.get(2))).at.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        try {
            taskList.deleteTask(10);
        } catch (Exception e) {
            assertEquals("The task does not exist on the list", e.getMessage());
        }
    }

    @Test
    public void deleteTask() {
        taskList.addToDo("test");
        assertEquals(1, taskList.tasks.size());
        assertEquals(false, taskList.tasks.get(0).isDone);
        taskList.addDeadline("test deadline",  LocalDate.parse("2020-12-12"));
        assertEquals(2, taskList.tasks.size());
        assertEquals("test deadline", taskList.tasks.get(1).description);
        taskList.addEvent("test event", LocalDate.parse("2020-10-10"));
        assertEquals(3, taskList.tasks.size());
        assertEquals("Oct 10 2020", ((Event)(taskList.tasks.get(2))).at.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        try {
            taskList.deleteTask(1);
            assertEquals(2, taskList.tasks.size());
        } catch (Exception e) {
            assertEquals("The task does not exist on the list", e.getMessage());
        }
    }

}
