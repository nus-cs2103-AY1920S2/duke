import duke.task.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    public void testTaskGenerator_correctInput_success() {
        try {
            String[] arr = "event get milk at 2020-02-02 2020".split("\\s");
            Task testTask = Task.generateTask(arr);
            assertEquals(testTask.getType(), "event");
            assertEquals(testTask.getStatus(), "F");
            assertTrue(testTask.getDescription().contains("get milk at Feb 2 2020 08:20PM"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testTaskGenerator_wrongInputWithMissingKeyword_exceptionThrown() {
        try {
            String[] arr = "deadline get milk on 2020-02-02 2020".split("\\s");
            Task.generateTask(arr);
            fail();
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("Keyword \"by\" missing"));
        }
    }

    @Test
    public void testSetDoneAndSetUndone_forTaskStatus() {
        try {
            String[] arr = "todo get milk".split("\\s");
            Task testTask = Task.generateTask(arr);
            assertEquals(testTask.getStatus(), "F");
            testTask.setDone();
            assertEquals(testTask.getStatus(), "T");
            testTask.setUndone();
            assertEquals(testTask.getStatus(), "F");
        } catch (Exception e) {
            fail();
        }
    }
}
