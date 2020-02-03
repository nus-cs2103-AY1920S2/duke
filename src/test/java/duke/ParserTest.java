package duke;

import org.junit.jupiter.api.Test;

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
        assertEquals(true, new Parser().parseCommand("delete", "delete"));
    }

    /**
     * Tests the time parsing of the Parser.
     */
    @Test
    public void testTimeParsing() {
        assertEquals("Jan 1 2019", Parser.reformatDateAndTime("2019-01-01"));
    }
}