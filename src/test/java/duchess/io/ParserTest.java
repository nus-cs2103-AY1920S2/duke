package duchess.io;

import duchess.command.Command;
import duchess.exception.DuchessException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * JUnit test class for {@code Parser}.
 */
public class ParserTest {
    /**
     * Tests the {@code parse} method of the {@code Parser} class when
     * valid input is given.
     *
     * @throws DuchessException If command is not recognized.
     */
    @Test
    public void parse_validCommand_success() throws DuchessException {
        assertEquals(Command.TODO, Parser.parse("todo go for a run"));
        assertEquals(Command.EVENT, Parser.parse("event celebrate cny /at 2-4pm"));
        assertEquals(Command.DEADLINE, Parser.parse("deadline finish ip deliverable /by wednesday"));
        assertEquals(Command.LIST, Parser.parse("list"));
        assertEquals(Command.BYE, Parser.parse("bye"));
        assertEquals(Command.DONE, Parser.parse("done 5"));
        assertEquals(Command.FIND, Parser.parse("find run"));
        assertEquals(Command.DELETE, Parser.parse("delete 2"));
        assertEquals(Command.HELP, Parser.parse("help"));
    }

    /**
     * Tests the {@code parse} method of the {@code Parser} class when
     * invalid input is given.
     */
    @Test
    public void parse_invalidCommand_exceptionThrown() {
        try {
            assertEquals(Command.TODO, Parser.parse("random command here!"));
            fail();
        } catch (DuchessException e) {
            assertEquals("I don't see what I can do with what you just told me.", e.getMessage());
        }
    }
}
