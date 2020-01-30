import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testStringConversion() {
        Event test = new Event("Event Test",
                LocalDateTime.of(2020, 1, 1, 0, 0));
        assertEquals("[E][✗] Event Test (at: Jan 1 2020 0000)", test.toString());
    }

    @Test
    public void testWriteFormatConversion() {
        Event test = new Event("Event Test",
                LocalDateTime.of(2020, 1, 1, 0, 0));
        assertEquals("E|Event Test|2020-01-01T00:00|0", test.writeFormat());
    }

    @Test
    public void checkCompletion_incompleteEvent_makeComplete() {
        Event test = new Event("Event Test",
                LocalDateTime.of(2020, 1, 1, 0, 0));
        assertEquals("E|Event Test|2020-01-01T00:00|1", test.makeCompleted().writeFormat());
    }
}
