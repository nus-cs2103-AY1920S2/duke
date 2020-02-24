import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ToDoTest {

    @Test
    public void testStringConversion() {
        assertEquals("[T][X] return book",
                new ToDo("return book").toString());
    }
}