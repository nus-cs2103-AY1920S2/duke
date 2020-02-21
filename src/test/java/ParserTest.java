import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void parseCommand_unrecognisedCommand_exceptionThrown() {
        try {
            assertEquals("hello", Parser.parseCommand("hello"));
            fail();
        } catch (GooseUnrecognisedException e) {
            assertEquals("Honk honk??", e.getMessage());
        }
    }
}
