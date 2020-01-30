import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testCommandParsing() {
        assertEquals(true, new Parser().parseCommand("delete", "delete"));
    }

    @Test
    public void testTimeParsing() {
        assertEquals("Jan 1 2019", Parser.parseTime("2019-01-01"));
    }
}