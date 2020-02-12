import grapie.commands.CommandTypes;
import grapie.exceptions.GrapieExceptions;
import grapie.functions.Parser;
import grapie.functions.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void testMakeSenseOfUserCommand() throws GrapieExceptions {
        assertEquals(CommandTypes.Commands.LIST, new Parser().parseCommand("list"));
        assertEquals(CommandTypes.Commands.DONE, new Parser().parseCommand("done 1"));
        assertEquals(CommandTypes.Commands.DELETE, new Parser().parseCommand("delete 1"));
        assertEquals(CommandTypes.Commands.ADD, new Parser().parseCommand("event read book /at 2019-11-11"));
        assertEquals(CommandTypes.Commands.ADD, new Parser().parseCommand("ahhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh"));
    }
}
