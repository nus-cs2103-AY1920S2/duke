import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testStringConversion() {
        assertEquals("[T][✗] borrow book", (new ToDo("borrow book")).toString());
    }
}
