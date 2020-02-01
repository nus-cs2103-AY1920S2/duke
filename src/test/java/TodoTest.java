import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TodoTest {
    @Test
    public void testName() {
        ToDo test = new ToDo("todoTest");
        assertEquals("todoTest", test.getName());
    }

    @Test
    public void testDone() {
        ToDo test = new ToDo("todoTest");
        assertEquals(false, test.getDone());
    }
}
