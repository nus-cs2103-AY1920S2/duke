import duke.commands.Parser;
import duke.tasks.Event;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    /**
     * test that output string is correct.
     */
    @Test
    public void toStringTest() {
        Event event = new Event("test", "28-1-2020 1200", Parser.PARSER);
        assertEquals("[E][\u2718] test (at: Jan 28 2020 1200PM)", event.toString());
    }

    /**
     * test that file data string is correct.
     */
    @Test
    public void fileStringTest() {
        Event event = new Event("test", "28-1-2020 1200", Parser.PARSER);
        assertEquals("E | N | test | Jan 28 2020 1200PM", event.fileString());
    }
}
