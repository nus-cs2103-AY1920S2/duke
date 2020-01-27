package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void getSaveRepresentation_validDescription_returnsSaveRepresentation() {
        Todo todoNotDoneTask = new Todo("I am not done");
        Todo todoDoneTask = new Todo("I am done", true);
        assertEquals("T|||false|||I am not done\n", todoNotDoneTask.getSaveRepresentation());
        assertEquals("T|||true|||I am done\n", todoDoneTask.getSaveRepresentation());
    }

    @Test
    public void toString_validDescription_returnsStringRepresentation() {
        Todo todoNotDoneTask = new Todo("I am not done");
        Todo todoDoneTask = new Todo("I am done", true);
        assertEquals("[T][\u2718] I am not done", todoNotDoneTask.toString());
        assertEquals("[T][\u2713] I am done", todoDoneTask.toString());
    }

    @Test
    public void getIsDone_taskDone_returnsIsDone() {
        Todo task1 = new Todo("I am done", true);
        Todo task2 = new Todo("I am done");
        task2.markDone();
        assertEquals(true, task1.getIsDone());
        assertEquals(true, task2.getIsDone());
    }

    @Test
    public void getIsDone_taskNotDone_returnsIsDone() {
        Todo task1 = new Todo("I am not done", false);
        Todo task2 = new Todo("I am not done");
        assertEquals(false, task1.getIsDone());
        assertEquals(false, task2.getIsDone());
    }
}
