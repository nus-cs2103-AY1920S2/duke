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
        try {
            assertEquals(new TodoCommand("todo task"), new Parser().parseCommand("todo task"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Tests the time parsing of the Parser.
     */
    @Test
    public void testTimeParsing() {
        try {
            assertEquals("Jan 1 2019", Parser.reformatDateAndTime("2019-01-01"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}