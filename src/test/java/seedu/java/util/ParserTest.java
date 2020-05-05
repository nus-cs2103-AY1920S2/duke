import seedu.java.util.Parser;
import seedu.java.util.Command;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    void testReadCommand() throws Exception {
        assertEquals(Command.BYE, Parser.readCommand("bye"));
    }
}