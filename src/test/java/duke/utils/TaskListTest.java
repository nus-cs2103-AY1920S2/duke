package duke.utils;

import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {

    TaskList taskList = new TaskList();

    @Test
    public void emptyTaskString() {
        assertEquals("No scheduled task yet", taskList.toString());
    }

    @Test
    public void addToListTest() {
        try {
            taskList.addToList("go to school", "todo");
            assertTrue(taskList.size() == 1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void removeTaskTest() {
        try {
            taskList.addToList("go to school", "todo");
            assertTrue(taskList.size() == 1);
            taskList.removeTask(0);
            assertTrue(taskList.size() == 0);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
