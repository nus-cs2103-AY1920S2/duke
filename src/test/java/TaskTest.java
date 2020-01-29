import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void getTotalTaskCount() {
        Task task1 = new Task("test1");
        Task task2 = new Task("test2");
        assertEquals(2, Task.getTotalTaskCount());
    }

    @Test
    void getDescription() {
        Task task = new Task("test");
        assertEquals("test", task.getDescription());
    }

    @Test
    void getStatusIcon_notDone() {
        Task task = new Task("test");
        assertEquals("✘", task.getDescription());
    }

    @Test
    void getStatusIcon_Done() {
        Task task = new Task("test");
        task.isDone = true;
        assertEquals("✓", task.getStatusIcon());
    }

    @Test
    void markAsDone() {
        Task task = new Task("test");
        try {
            task.markAsDone();
            assertEquals("✓", task.getStatusIcon());
        } catch (DuplicateMarkAelitaException e) {
            Assertions.fail();
        }
    }

    @Test
    void markAsDone_duplicateDone_exceptionThrown() {
        Task task = new Task("test");
        task.isDone = true;
        try {
            task.markAsDone();
            Assertions.fail();
        } catch (DuplicateMarkAelitaException e) {
            assertEquals("✓", task.getStatusIcon());
        }
    }

    @Test
    void setTotalTaskCount() {

        try {
            Task.setTotalTaskCount(2);
            assertEquals(2, Task.totalTaskCount);
        } catch (InvalidArgumentAelitaException e) {
            Assertions.fail();
        }
    }

    @Test
    void setTotalTaskCount_negativeArgument_exceptionThrown() {

        try {
            Task.setTotalTaskCount(-2);
            Assertions.fail();
        } catch (InvalidArgumentAelitaException e) {
            assertEquals(0, Task.totalTaskCount);
        }
    }

    @Test
    void testToString() {
        Task test = new Task("Test 1");
        assertEquals("[✘] Test 1", test.toString());
    }
}