package duke;

import duke.main.Parser;
import duke.task.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void parseCommand_unknownCommand() {
        List<Task> dummyTaskList = new ArrayList<>();
        assertTrue(Parser.parseCommand("hello", dummyTaskList));
    }
}
