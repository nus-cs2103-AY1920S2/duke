import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EventTest {

    private Event newEvent() {
        return new Event("reading event", "at", "2019-02-13");
    }

    @Test
    void taskStateString() {
        assertEquals("[✗]", newEvent().taskStateString());
    }

    @Test
    void markDone() {
        assertEquals(true, newEvent().markDone());
    }

    @Test
    void formattedDate() {
        assertEquals("Feb 13 2019", newEvent().formattedDate());
    }

    @Test
    void testToString() {
        assertEquals("[E][✗] reading event at Feb 13 2019", newEvent().toString());
    }
}