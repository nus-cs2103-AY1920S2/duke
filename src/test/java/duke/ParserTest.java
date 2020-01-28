package duke;

import duke.command.AddCommand;
import duke.command.DoneCommand;
import duke.command.DeleteCommand;
import duke.command.GetCommand;
import duke.command.ListCommand;
import duke.command.ExitCommand;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
 
class ParserTest {
    @Test
    void parse_validCommand_success() throws Exception {
        assertTrue(Parser.parse("todo read book") instanceof AddCommand);
        assertTrue(Parser.parse("deadline return book /by 2020-02-02 18:00") instanceof AddCommand);
        assertTrue(Parser.parse("event project meeting /at 2020-01-27 14:00-16:00") instanceof AddCommand);
        assertTrue(Parser.parse("done 2") instanceof DoneCommand);
        assertTrue(Parser.parse("delete 2") instanceof DeleteCommand);
        assertTrue(Parser.parse("get 2020-02-02") instanceof GetCommand);
        assertTrue(Parser.parse("list") instanceof ListCommand);
        assertTrue(Parser.parse("bye") instanceof ExitCommand);
    }

    @Test
    void parse_invalidCommand_exceptionThrown() {
        try {
            Parser.parse("todo");
            fail();
        } catch (DukeException e) {
            assertEquals("The description of a todo cannot be empty.", e.getMessage());
        }
        try {
            Parser.parse("deadline");
            fail();
        } catch (DukeException e) {
            assertEquals("The description and due date of a deadline cannot be empty.", e.getMessage());
        }
        try {
            Parser.parse("event");
            fail();
        } catch (DukeException e) {
            assertEquals("The description and date and time of an event cannot be empty.", e.getMessage());
        }
        try {
            Parser.parse("done");
            fail();
        } catch (DukeException e) {
            assertEquals("The ID of the task done cannot be empty.", e.getMessage());
        }
        try {
            Parser.parse("done return book");
            fail();
        } catch (DukeException e) {
            assertEquals("The ID of the task done should be a number.", e.getMessage());
        }
        try {
            Parser.parse("delete");
            fail();
        } catch (DukeException e) {
            assertEquals("The ID of the task to delete cannot be empty.", e.getMessage());
        }
        try {
            Parser.parse("delete return book");
            fail();
        } catch (DukeException e) {
            assertEquals("The ID of the task to delete should be a number.", e.getMessage());
        }
        try {
            Parser.parse("get");
            fail();
        } catch (DukeException e) {
            assertEquals("The date of tasks to retrieve cannot be empty.", e.getMessage());
        }
        try {
            Parser.parse("get 02-02-2020");
            fail();
        } catch (DukeException e) {
            assertEquals("Incorrect date format. Format required: yyyy-mm-dd", e.getMessage());
        }
        try {
            Parser.parse("find");
            fail();
        } catch (DukeException e) {
            assertEquals("The keyword to search cannot be empty.", e.getMessage());
        }
    }

    @Test
    void parse_unknownCommand_exceptionThrown() {
        try {
            Parser.parse("xxx");
            fail();
        } catch (DukeException e) {
            assertEquals("I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }
}
