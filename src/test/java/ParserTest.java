import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    
    @Test
    void getCommand_valid_success() {
        try {
            assertEquals(Command.DEADLINE, new Parser("deadline word1 word2 /by 2020-01-01").getCommand());
        } catch (InvalidInstructionException e) {
            fail();
        }
    }

    @Test
    void getCommand_unrecognizedCommand_exceptionThrown() {
        try {
            assertEquals(Command.DEADLINE, new Parser("qwerty word1 word2 /by 2020-01-01").getCommand());
            fail();
        } catch (InvalidInstructionException e) {
            assertEquals("Command \"qwerty\" is not recognized", e.getMessage());
        }
    }
}
