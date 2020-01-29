import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void taskStateString() {
        assertEquals("[✗]", new ToDo("read").taskStateString());
    }

    @Test
    void markDone() {
        ToDo test = new ToDo("read");
        assertEquals(false, test.isDone);
        test.markDone();
        assertEquals(true, test.isDone);
    }

    @Test
    void testToString() {
        assertEquals("[T][✗] read", new ToDo("read").toString());
    }
}