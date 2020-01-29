import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void convert() {
        String s = "1/2/2013 0000";
        assertEquals("1st of February 2013, 12:00am", Parser.convertDateAndTime(s));
    }
}