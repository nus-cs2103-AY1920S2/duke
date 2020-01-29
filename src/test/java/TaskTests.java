import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTests {
    @Test
    public void testDeadline() {
        Deadline deadlineTest = new Deadline("hello", LocalDate.parse("2023-11-11"));
        assertEquals("hello", deadlineTest.description);
    }

    @Test
    public void testEvent() {
        Event eventTest = new Event("hello", "Changi");
        assertEquals(false, eventTest.isDone);
    }



}

