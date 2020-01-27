package duke.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void initialiseShouldGetFalseForStatus() {
        Task task = new Task("Test");
        Assertions.assertFalse(task.getStatus());
    }

    @Test
    public void setStatusDone_shouldReturnTrueForStatus() {
        Task task = new Task("Test");
        task.setStatusDone();
        Assertions.assertTrue(task.getStatus());
    }

    @Test
    public void toString_shouldReturnInCorrectFormat() {
        Task task = new Task("Test");
        String expected = "[\u2718] Test";
        Assertions.assertEquals(expected, task.toString());
    }
}