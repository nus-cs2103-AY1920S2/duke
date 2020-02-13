package duke;

import duke.main.Parser;
import duke.task.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseCommand_unknownCommand() {
        TaskList dummyTaskList = new TaskList();
        assertEquals(("☹ OOPS!!! I'm sorry, but I don't know what that means :-("),
            Parser.parseCommand("hello", dummyTaskList));
    }
}
