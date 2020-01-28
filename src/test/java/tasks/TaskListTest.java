package tasks;

import org.junit.jupiter.api.Test;
import tasks.TaskList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void getTaskCount_newTaskList_success() {
        assertEquals(0, new TaskList().getTaskCount());
    }

    @Test
    public void addNewTask_invalidDay_ExceptionThrown() {
        try {
            String text = "deadline test /by 1986-12-35 12:00";
            String[] textArray = text.split(" ");
            new TaskList().addNewTask(textArray);
            fail();
        } catch (Exception e) {
            assertEquals("Make sure you entered valid date: yyyy-MM-dd HH:mm", e.getMessage());
        }
    }
}
