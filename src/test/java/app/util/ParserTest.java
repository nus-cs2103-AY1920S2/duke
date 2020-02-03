package app.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parseStringToPair_multipleWords() {
        StringPair output = Parser.parse("test string");
        assertEquals("test", output.getFirstValue());
        assertEquals("string", output.getSecondValue());
    }

    @Test
    public void parseStringToPair_oneWord() {
        StringPair output = Parser.parse("test");
        assertEquals("test", output.getFirstValue());
        assertEquals("", output.getSecondValue());
    }
}