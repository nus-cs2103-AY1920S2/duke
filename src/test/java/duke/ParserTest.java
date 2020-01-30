package duke;

import duke.exception.DukeException;
import duke.main.Parser;
import duke.task.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void parseCommand_unknownCommand() throws DukeException {
        TaskList dummyTaskList = new TaskList();
        assertTrue(Parser.parseCommand("hello", dummyTaskList));
    }
}
