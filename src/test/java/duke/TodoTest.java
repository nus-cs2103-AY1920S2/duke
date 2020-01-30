package duke;

import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void parseCommand_unknownCommand() {
        assertEquals("1.[T][✗] test todo",
            new Todo(false, 0, "test todo").toString());
    }
}
