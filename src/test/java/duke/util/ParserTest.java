package duke.util;

import duke.command.DeleteCommand;
import duke.exception.DukeInvalidArgumentFormatException;
import duke.exception.DukeInvalidDateFormatException;
import duke.exception.DukeUnknownKeywordException;
import org.junit.jupiter.api.Test;
import duke.stub.TaskListStub;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/*
 * ParserTest
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 28 Jan 2020
 *
 */

/**
 * <p>ParserTest class is a test class for Parser class.</p>
 * @author Mario Lorenzo
 */

public class ParserTest {

    /**
     * Tests the Parser's parse function for the valid format.
     */

    @Test
    public void parse_validFormat_success() {
        try {
            System.out.println(new Parser().parse("delete 1", new TaskListStub()));
            assertEquals(new DeleteCommand(1), new Parser().parse("delete 1", new TaskListStub()));
        } catch (DukeUnknownKeywordException | DukeInvalidArgumentFormatException | DukeInvalidDateFormatException e) {
            System.err.println(e);
        }

    }

    /**
     * Tests the Parser's parse function for the invalid format.
     */

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
