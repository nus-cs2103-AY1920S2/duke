import bot.command.CommandParser;

import bot.command.exception.InadequateArgumentsException;
import bot.command.exception.TooManyArgumentsException;
import bot.command.exception.UnknownInstructionException;

import bot.command.instruction.ParsedInstruction;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class CommandParserTest {
    @Test(expected = UnknownInstructionException.class)
    public void parse_randomWords_exceptionThrown() throws InadequateArgumentsException,
            UnknownInstructionException, TooManyArgumentsException
    {
        new CommandParser().parse("beehive valley spring darkness");
    }

    @Test
    public void parse_event_success() throws InadequateArgumentsException,
            UnknownInstructionException, TooManyArgumentsException
    {
        CommandParser comParse = new CommandParser();
        ParsedInstruction parsed = comParse.parse("event party /at my place");
        assertEquals(parsed.getInstruction(), CommandParser.ALL_INSTRUCTIONS[2]);
        assertEquals(parsed.getArguments(),
                new ArrayList<String>(Arrays.asList("party", "my place")));
    }

    @Test
    public void parse_deadline_success() throws InadequateArgumentsException,
            UnknownInstructionException, TooManyArgumentsException
    {
        CommandParser comParse = new CommandParser();
        ParsedInstruction parsed = comParse.parse(
                "deadline project /by 7-11-2013-1400");
        assertEquals(parsed.getInstruction(), CommandParser.ALL_INSTRUCTIONS[0]);
        assertEquals(parsed.getArguments(),
                new ArrayList<String>(Arrays.asList("project", "7-11-2013-1400")));
    }
}
