import exception.DuplicateMarkAelitaException;
import exception.InvalidArgumentAelitaException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import task.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTest {

    //Class variables does not reset after a test ends

    @Test
    void getTotalTaskCount() {

        Task task1 = new Task("test1");
        Task task2 = new Task("test2");
        assertEquals(2, Task.getTotalTaskCount());
        try {
            Task.setTotalTaskCount(0);
        } catch (InvalidArgumentAelitaException e) {
            Assertions.fail();
        }
    }

    @Test
    void getDescription() {

        Task task = new Task("test");
        assertEquals("test", task.getDescription());
        try {
            Task.setTotalTaskCount(0);
        } catch (InvalidArgumentAelitaException e) {
            Assertions.fail();
        }
    }

    @Test
    void getStatusIcon_notDone() {

        assertEquals(0, Task.getTotalTaskCount());
        Task task = new Task("test");
        assertEquals("✘", task.getStatusIcon());
        try {
            Task.setTotalTaskCount(0);
        } catch (InvalidArgumentAelitaException e) {
            Assertions.fail();
        }
    }

    @Test
    void markAsDone() {

        assertEquals(0, Task.getTotalTaskCount());
        Task task = new Task("test");
        try {
            task.markAsDone();
            assertEquals("✓", task.getStatusIcon());
        } catch (DuplicateMarkAelitaException e) {
            Assertions.fail();
        } finally {
            try {
                Task.setTotalTaskCount(0);
            } catch (InvalidArgumentAelitaException e) {
                Assertions.fail();
            }

        }
    }

    @Test
    void setTotalTaskCount() {

        assertEquals(0, Task.getTotalTaskCount());
        try {
            Task.setTotalTaskCount(2);
            assertEquals(2, Task.getTotalTaskCount());
        } catch (InvalidArgumentAelitaException e) {
            Assertions.fail();
        } finally {
            try {
                Task.setTotalTaskCount(0);
            } catch (InvalidArgumentAelitaException e) {
                Assertions.fail();
            }
        }
    }

    @Test
    void setTotalTaskCount_negativeArgument_exceptionThrown() {

        assertEquals(0, Task.getTotalTaskCount());
        try {
            Task.setTotalTaskCount(-2);
            Assertions.fail();
        } catch (InvalidArgumentAelitaException e) {
            assertEquals(0, Task.getTotalTaskCount());
        } finally {
            try {
                Task.setTotalTaskCount(0);
            } catch (InvalidArgumentAelitaException e) {
                Assertions.fail();
            }
        }
    }

    @Test
    void testToString() {

        assertEquals(0, Task.getTotalTaskCount());
        Task test = new Task("Test 1");
        assertEquals("[✘] Test 1", test.toString());
        try {
            Task.setTotalTaskCount(0);
        } catch (InvalidArgumentAelitaException e) {
            Assertions.fail();
        }

    }
}