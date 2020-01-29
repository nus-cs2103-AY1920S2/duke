import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    //Class variables does not reset after a test ends

    @Test
    void getTotalTaskCount() {
        Task task1 = new Task("test1");
        Task task2 = new Task("test2");
        assertEquals(2, Task.getTotalTaskCount());
        Task.totalTaskCount = 0;
    }

    @Test
    void getDescription() {
        Task task = new Task("test");
        assertEquals("test", task.getDescription());
        Task.totalTaskCount = 0;
    }

    @Test
    void getStatusIcon_notDone() {
        assertEquals(0, Task.totalTaskCount);
        Task task = new Task("test");
        assertEquals("\u2718", task.getStatusIcon());
        Task.totalTaskCount = 0;
    }

    @Test
    void getStatusIcon_Done() {
        assertEquals(0, Task.totalTaskCount);
        Task task = new Task("test");
        task.isDone = true;
        assertEquals("\u2713", task.getStatusIcon());
        Task.totalTaskCount = 0;
    }

    @Test
    void markAsDone() {
        assertEquals(0, Task.totalTaskCount);
        Task task = new Task("test");
        try {
            task.markAsDone();
            assertEquals("\u2713", task.getStatusIcon());
        } catch (DuplicateMarkAelitaException e) {
            Assertions.fail();
        } finally {
            Task.totalTaskCount = 0;
        }
    }

    @Test
    void markAsDone_duplicateDone_exceptionThrown() {
        assertEquals(0, Task.totalTaskCount);
        Task task = new Task("test");
        task.isDone = true;
        try {
            task.markAsDone();
            Assertions.fail();
        } catch (DuplicateMarkAelitaException e) {
            assertEquals("\u2713", task.getStatusIcon());
        } finally {
            Task.totalTaskCount = 0;
        }
    }

    @Test
    void setTotalTaskCount() {
        assertEquals(0, Task.totalTaskCount);
        try {
            Task.setTotalTaskCount(2);
            assertEquals(2, Task.totalTaskCount);
        } catch (InvalidArgumentAelitaException e) {
            Assertions.fail();
        } finally {
            Task.totalTaskCount = 0;
        }
    }

    @Test
    void setTotalTaskCount_negativeArgument_exceptionThrown() {
        assertEquals(0, Task.totalTaskCount);
        try {
            Task.setTotalTaskCount(-2);
            Assertions.fail();
        } catch (InvalidArgumentAelitaException e) {
            assertEquals(0, Task.totalTaskCount);
        } finally {
            Task.totalTaskCount = 0;
        }
    }

    @Test
    void testToString() {
        assertEquals(0, Task.totalTaskCount);
        Task test = new Task("Test 1");
        assertEquals("[\u2718] Test 1", test.toString());
        Task.totalTaskCount = 0;
    }
}