import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.Deadline;
import duke.TaskList;
import duke.ToDo;
import org.junit.jupiter.api.Test;

/**
 * Represents a class that is used to write JUnit tests for the duke application.
 */
public class DukeTest {
    @Test
    public void testToDo() {
        ToDo todo = new ToDo("read book");
        assertEquals("[T][✘] read book", todo.toString());
        TaskList tk = new TaskList();
        tk.addTask("todo", "watch movie");
        tk.markDone(1);
        assertEquals("[T][✓] watch movie", tk.getTaskByIndex(1).toString());
    }

    @Test
    public void testDeadline() {
        Deadline deadline = new Deadline("assignment 1", "Mon 2 pm");
        assertEquals("[D][✘] assignment 1(by: Mon 2 pm)", deadline.toString());
    }
}
