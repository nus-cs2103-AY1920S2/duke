package duke.command;

import static org.junit.jupiter.api.Assertions.assertThrows;

import duke.exception.InvalidCommandException;
import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void parseCommand_invalidCommand_throwsInvalidCommandException() {
        assertThrows(InvalidCommandException.class, () -> Parser.parseCommand("not a command"));
        assertThrows(InvalidCommandException.class, () -> Parser.parseCommand("by e"));
        assertThrows(InvalidCommandException.class, () -> Parser.parseCommand("LiSt"));
    }
}
