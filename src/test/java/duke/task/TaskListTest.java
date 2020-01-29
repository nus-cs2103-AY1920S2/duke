package duke.task;

import duke.exception.InvalidCommandException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {
    @Test
    public void setAsDone_indexNotInList_throwsInvalidCommandException() {
        TaskList tasks = new TaskList(new ArrayList<Task>());
        tasks.addTask(new Todo("dummy task"));

        assertThrows(InvalidCommandException.class, () -> tasks.setAsDone(0));
        assertThrows(InvalidCommandException.class, () -> tasks.setAsDone(2));
        assertThrows(InvalidCommandException.class, () -> tasks.setAsDone(10));
    }

    @Test
    public void getTask_validIndex_returnsTask() {
        TaskList tasks = new TaskList(new ArrayList<Task>());
        Todo task1 = new Todo("dummy task1");
        Todo task2 = new Todo("dummy task2");
        Todo task3 = new Todo("dummy task3");

        tasks.addTask(task1);
        tasks.addTask(task2);
        tasks.addTask(task3);

        assertEquals(task1, tasks.getTask(1));
        assertEquals(task2, tasks.getTask(2));
        assertEquals(task3, tasks.getTask(3));
    }

    @Test
    public void getTask_invalidIndex_throwsIndexOutOfBoundsException() {
        TaskList tasks = new TaskList(new ArrayList<Task>());
        Todo task1 = new Todo("dummy task1");
        Todo task2 = new Todo("dummy task2");
        Todo task3 = new Todo("dummy task3");

        tasks.addTask(task1);
        tasks.addTask(task2);
        tasks.addTask(task3);

        assertThrows(IndexOutOfBoundsException.class, () -> tasks.getTask(0));
        assertThrows(IndexOutOfBoundsException.class, () -> tasks.getTask(4));
        assertThrows(IndexOutOfBoundsException.class, () -> tasks.getTask(10));
    }
}
