package test.java.duke.parser;

import duke.entity.command.AddCommand;
import duke.entity.task.Event;
import duke.entity.task.Todo;
import duke.handler.Ui;
import duke.parser.CommandParser;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CommandParserTest {

    @Test
    public void parse_todoCommand_success() {
        assertEquals(new AddCommand(new Todo("read book")).getNewTask().getTaskName(), ((AddCommand) new CommandParser(new Ui()).parse("todo read book")).getNewTask().getTaskName());
    }
}
