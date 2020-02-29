package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {
    @Test
    public void testStringConversion() {
        assertEquals("[E][✘]project meeting (at: Aug 8 2020)", new Event("project meeting", "2020-08-08").toString());
    }

    @Test
    public void testStringInFileConversion() {
        assertEquals("E # 0 # project meeting # 2020-08-08", new Event("project meeting", "2020-08-08").toStringInFile());
    }
}
