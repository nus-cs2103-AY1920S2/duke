import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void testParseTime() {
        assertEquals("4 Jun 2000, 6:09 PM", Parser.parseTime("04-06-2000 18:09"));
    }
}
