package main.java.test.java.duke.parser;

import main.java.duke.entity.command.AddCommand;
import main.java.duke.entity.task.Todo;
import main.java.duke.parser.CommandParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CommandParserTest {

    @Test
    public void parse_todoCommand_success() {
        assertEquals(new AddCommand(new Todo("read book")).getNewTask().getTaskName(), ((AddCommand) new CommandParser().parse("todo read book")).getNewTask().getTaskName());
    }
}
