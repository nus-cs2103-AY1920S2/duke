import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {

    @Test
    public void testStringConversion() {
        assertEquals("[E][✘] drink tea (by: Nov 20 2019)", new Deadline("drink coffee", "2019-11-20"));
    }
}