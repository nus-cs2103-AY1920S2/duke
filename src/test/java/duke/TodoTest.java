package duke;

import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void addTodoTest() {
        ToDo todo = new ToDo("Test ToDo Item");
        assertEquals("[T][✘] Test ToDo Item", todo.toString());
    }
    @Test
    public void addDoneTodoTest() {
        ToDo todo = new ToDo("Test ToDo Item");
        todo.markAsDone();
        assertEquals("[T][✓] Test ToDo Item", todo.toString());
    }
}