import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest{
    @Test
    public void testDoneStatus() {
        Event eventTest = new Event("test", "2020-01-01");
        assertEquals(false, eventTest.getDoneStatus());
    }

    @Test
    public void testTaskName() {
        Event eventTest = new Event("test", "2020-01-01");
        assertEquals("test", eventTest.getTaskName());
    }
}
