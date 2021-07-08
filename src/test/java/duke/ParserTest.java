package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void getCommand_validInput_Success() {
        try {
            assertEquals(Command.DONE, new Parser("done 2").getCommand());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void getCommand_InvalidInput_exceptionThrown() {
        try {
            assertEquals(Command.BYE, new Parser("byee"));
        } catch (DukeException e) {
            assertEquals("Invalid Command.", e.getMessage());
        }
    }
}
