import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest{
    @Test
    public void toStringTest() {
        Event event = new Event("test", "28-1-2020 1200", Task.parser);
        assertEquals("[E][N] test (at: Jan 28 2020 12PM)", event.toString());
    }

    @Test
    public void fileStringTest() {
        Event event = new Event("test", "28-1-2020 1200", Task.parser);
        assertEquals("E | N | test | Jan 28 2020 12PM", event.fileString());
    }
}