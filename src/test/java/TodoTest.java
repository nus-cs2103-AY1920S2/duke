import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testGetInfo() {
        assertEquals("T|0|return book", new Todo("return book", 1).getInfo());
    }

    @Test
    public void testToString() {
        assertEquals("[T][✘] return book", new Todo("return book", 1).toString());
    }

}
