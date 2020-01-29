package duke.command;

import duke.exception.InvalidCommandException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void parseCommand_invalidCommand_throwsInvalidCommandException() {
        assertThrows(InvalidCommandException.class, () -> Parser.parseCommand("not a command"));
        assertThrows(InvalidCommandException.class, () -> Parser.parseCommand("by e"));
        assertThrows(InvalidCommandException.class, () -> Parser.parseCommand("LiSt"));
    }
}
