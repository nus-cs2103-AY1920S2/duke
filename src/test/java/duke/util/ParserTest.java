package duke.util;

import duke.command.DeleteCommand;
import duke.exception.DukeInvalidArgumentFormatException;
import duke.exception.DukeInvalidDateFormatException;
import duke.exception.DukeUnknownKeywordException;
import org.junit.jupiter.api.Test;
import duke.stub.TaskListStub;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parse_validFormat_success() {
        try {
            System.out.println(new Parser().parse("delete 1", new TaskListStub()));
            assertEquals(new DeleteCommand(1), new Parser().parse("delete 1", new TaskListStub()));
        } catch (DukeUnknownKeywordException | DukeInvalidArgumentFormatException | DukeInvalidDateFormatException e) {
            System.err.println(e);
        }

    }
    @Test
    public void parse_invalidNumber_exceptionThrown() {
        try {
            assertEquals(new DeleteCommand(1), new Parser().parse("delete lol",
                    new TaskListStub()));
            fail();
        } catch (DukeUnknownKeywordException | DukeInvalidArgumentFormatException | DukeInvalidDateFormatException e) {
            assertEquals("☹ OOPS!!! " +
                    "The argument for 'delete' command requires a number.", e.getMessage());
        }
    }
}
