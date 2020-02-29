package duke.core;

import org.junit.jupiter.api.Test;

import duke.command.TodoCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests written for the Parser class.
 */
public class ParserTest {
    /**
     * Tests the command parsing of the Parser.
     */
    @Test
    public void testCommandParsing() {
        assertEquals(new TodoCommand("todo task", false), new Parser().parseCommand("todo task"));
    }

    /**
     * Tests the time parsing of the Parser.
     */
    @Test
    public void testTimeParsing() {
        assertEquals("Jan 1 2019", Parser.reformatDateAndTime("2019-01-01"));
    }
}