package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseCommand() {
        assertEquals("list", new Parser().parseCommand("list"));
        assertEquals("done", new Parser().parseCommand("done 5"));
        assertEquals("delete", new Parser().parseCommand("delete 10"));
        assertEquals("todo", new Parser().parseCommand("todo hello"));
        assertEquals("bye", new Parser().parseCommand("bye"));
        assertEquals("invalid command", new Parser().parseCommand("hi"));
    }
}
