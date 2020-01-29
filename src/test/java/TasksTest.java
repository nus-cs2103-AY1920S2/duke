package packagedirectory.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TasksTest {
    @Test
    public void testStringConversion() {
        Message msg = new Message("read book");
        assertEquals("[x] read book\n", new Tasks(msg).toString());
    }
}
