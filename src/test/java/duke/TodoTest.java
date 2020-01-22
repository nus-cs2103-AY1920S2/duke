package duke;

import duke.task.Task;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void parseTest() {
        String taskString = "T | 0 | borrow book";

        String[] parts = taskString.split("\\|");
        Task todo = Todo.parse(taskString);
        assert (!todo.isDone());
        assert (todo.getType().equals("T"));
        assert (todo.getDescription().equals("borrow book"));
    }
}