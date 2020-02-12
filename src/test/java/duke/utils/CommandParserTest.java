package duke.utils;

import duke.command.Command;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.UnknownCommand;
import duke.command.FindCommand;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandParserTest {

    @Test
    public void exitCommandTest() {
        Command command = CommandParser.commandParser("bye bye");
        assertTrue(command instanceof ExitCommand);
    }

    @Test
    public void singleDoneCommandTest() {
        Command command = CommandParser.commandParser("done 1");
        assertTrue(command instanceof DoneCommand);
    }

    @Test
    public void multipleDoneCommandTest() {
        Command command = CommandParser.commandParser("done 1 5 6");
        assertTrue(command instanceof DoneCommand);
    }

    @Test
    public void invalidDoneCommandTest() {
        Command command = CommandParser.commandParser("done asdfa");
        assertTrue(command instanceof DoneCommand);
    }

    @Test
    public void singleDeleteCommandTest() {
        Command command = CommandParser.commandParser("delete 1");
        assertTrue(command instanceof DeleteCommand);
    }

    @Test
    public void multipleDeleteCommandTest() {
        Command command = CommandParser.commandParser("delete 1 5 6");
        assertTrue(command instanceof DeleteCommand);
    }

    @Test
    public void invalidDeleteCommandTest() {
        Command command = CommandParser.commandParser("delete asdfa");
        assertTrue(command instanceof DeleteCommand);
    }

    @Test
    public void listCommandTest() {
        Command command = CommandParser.commandParser("list asdfsadf");
        assertTrue(command instanceof ListCommand);
    }

    @Test
    public void todoAddCommandTest() {
        Command command = CommandParser.commandParser("todo asdfsadf");
        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void eventAddCommandTest() {
        Command command = CommandParser.commandParser("event asdfsadf");
        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void deadlineAddCommandTest() {
        Command command = CommandParser.commandParser("deadline asdfsadf");
        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void findCommandTest() {
        Command command = CommandParser.commandParser("find asdfsadf");
        assertTrue(command instanceof FindCommand);
    }

    @Test
    public void unknownCommandTest() {
        Command command = CommandParser.commandParser("asdfasfadf");
        assertTrue(command instanceof UnknownCommand);
    }

}
