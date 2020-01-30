import bot.command.CommandParser;
import bot.command.exception.UnknownInstructionException;
import bot.command.Instruction;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CommandParserTest {
    @Test(expected = UnknownInstructionException.class)
    public void parse_randomWords_exceptionThrown() throws UnknownInstructionException {
        new CommandParser().parse("beehive valley spring darkness");
    }

    @Test
    public void parse_event_success() throws UnknownInstructionException {
        assertEquals(new CommandParser().parse("event party /at my place"),
                Instruction.STORE_EVENT);
    }

    @Test
    public void parse_deadline_success() throws UnknownInstructionException {
        assertEquals(new CommandParser().parse("deadline project"),
                Instruction.STORE_DDL);
    }
}
