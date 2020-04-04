import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;


public class ToDoTest {

    @Test

    public void testStringConversion() {
        assertEquals("[T][✗]test todo", new ToDo("test todo").toString());
    }

    @Test
    public void testGetType() {
        assertEquals("todo", new ToDo("test todo type").getType());
    }

}
