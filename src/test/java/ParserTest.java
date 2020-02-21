import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.parser.Parser;
import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void parse_existingCommand_parsesCommand() {
        Parser parser = new Parser();
        Command command;
        try {
            command = Parser.parse("bye");
            assertTrue(command instanceof ExitCommand);
        } catch (Exception e) {
            fail("Should not have thrown Exception.");
        }
    }
}