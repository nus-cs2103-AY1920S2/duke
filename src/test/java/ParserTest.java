import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void typeTest() {
        assertEquals("todo", new Parser().getType("todo eat"));
        assertEquals("event", new Parser().getType("event sleep /at 12pm"));
        assertEquals("deadline", new Parser().getType("deadline sleep /by 1pm"));
    }
}
