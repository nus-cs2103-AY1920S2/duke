import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDosTest{
    @Test
    public void testDoneStatus() {
        ToDos todoTest = new ToDos("test");
        assertEquals(false, todoTest.getDoneStatus());
    }

    @Test
    public void testTaskName() {
        ToDos todoTest = new ToDos("test");
        assertEquals("test", todoTest.getTaskName());
    }
}
