import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskTest {
    @Test
    public void testStringConversion() {
        assertEquals("[T][ X ] eat apples", new Todo("eat apples").toString());
    }

    @Test
    public void testStatusIcon() {
        Todo eat = new Todo("eat");
        assertEquals(" X ", eat.getStatusIcon());
        eat.markAsDone();
        assertEquals("OK!", eat.getStatusIcon());
    }
}
