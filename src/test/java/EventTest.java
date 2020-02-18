import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EventTest {
    @Test
    public void testName() {
        Event test = new Event("eventTest", "2020-06-06");
        assertEquals("eventTest", test.getName());
    }

    @Test
    public void testDone() {
        Event test = new Event("eventTest", "2020-06-06");
        assertEquals(false, test.getDone());
    }
}
