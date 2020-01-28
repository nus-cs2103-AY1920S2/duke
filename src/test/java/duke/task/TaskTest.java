package duke.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * <h1>TaskTest Class</h1>
 * Test for the Task class
 *
 * @author  Eng Xuan En
 */
public class TaskTest {

    /**
     * Test if the method, getStatus, will give false or not.
     */
    @Test
    public void initialiseShouldGetFalseForStatus() {
        Task task = new Task("Test");
        Assertions.assertFalse(task.getStatus());
    }

    /**
     * Test if the method, getStatus, will return true after running setStatusDone method.
     */
    @Test
    public void setStatusDone_shouldReturnTrueForStatus() {
        Task task = new Task("Test");
        task.setStatusDone();
        Assertions.assertTrue(task.getStatus());
    }

    /**
     * Test if toString method give correct String format.
     */
    @Test
    public void toString_shouldReturnInCorrectFormat() {
        Task task = new Task("Test");
        String expected = "[\u2718] Test";
        Assertions.assertEquals(expected, task.toString());
    }
}