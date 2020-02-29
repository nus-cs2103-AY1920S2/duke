package duke.util;

import duke.exception.CommandNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void getCommandIdentifier_commandFound_success() throws Exception {
        Parser parser = new Parser();
        assertEquals(CommandIdentifier.BYE, parser.getCommandIdentifier("bye"));
    }

    @Test
    public void getCommandIdentifier_commandNotFound_exceptionThrown() {
        try {
            Parser parser = new Parser();
            assertEquals(CommandIdentifier.BYE, parser.getCommandIdentifier("hey"));
            fail();
        } catch (CommandNotFoundException e) {
            assertEquals("OOPS!!! I don't understand this command. ☹\n", e.getMessage());
        }
    }
}
