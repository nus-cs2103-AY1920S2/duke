package duke.pack;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testFormatForFile() {
        assertEquals("T | 0 | read book\n", new Todo("read book").formatForFile());
    }

}
