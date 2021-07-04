import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DeadlineTest {
    @Test
    public void testName() {
        Deadline test = new Deadline("deadlineTest", "2020-06-06");
        assertEquals("deadlineTest", test.getName());
    }

    @Test
    public void testDone() {
        Deadline test = new Deadline("deadlineTest", "2020-06-06");
        assertEquals(false, test.getDone());
    }
}
