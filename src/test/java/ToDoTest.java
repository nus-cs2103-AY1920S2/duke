import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void testStringConversion() {
        assertEquals("[T][✗] read book", new ToDo(" read book").toString());
        assertEquals("[T][✗] join club", new ToDo(" join club").toString());
    }
}


