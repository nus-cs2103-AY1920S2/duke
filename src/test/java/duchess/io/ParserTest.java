package duchess.io;

import duchess.command.Command;
import duchess.exception.DuchessException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
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
