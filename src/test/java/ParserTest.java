

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Parser;
import duke.common.command.ExitCommand;

public class ParserTest {

    @Test
    public void test_exit_success() throws DukeException {
        assertEquals(Parser.parse("bye") instanceof ExitCommand, true);
    }
}