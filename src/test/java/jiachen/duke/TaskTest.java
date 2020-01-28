package jiachen.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTest {

    @Test
    void getStatusIcon() {
        Task task = new MockTask("this is a description");
        assertEquals(task.getStatusIcon(), ("\u2718"));
        task.markAsDone();
        assertEquals(task.getStatusIcon(), "\u2713");
    }

    @Test
    void markAsDone() {
        Task task = new MockTask("this is a description");
        assert (!task.isDone);
        task.markAsDone();
        assert (task.isDone);
    }

    @Test
    void testToString() {
        Task task = new MockTask("this is a description");
        assertEquals(task.toString(), "[\u2718] this is a description");
    }

    @Test
    void format() {
        Task task = new MockTask("this is a description");
        assertEquals(task.format(), "0 | this is a description");
    }
}