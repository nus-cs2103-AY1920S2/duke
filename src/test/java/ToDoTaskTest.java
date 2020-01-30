import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ToDoTaskTest {

    @Test
    public void testStringConversion() {
        assertEquals("[T][\u2718] sleep", new ToDoTask("sleep").toString());
    }
}