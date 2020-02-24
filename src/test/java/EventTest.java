import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {

    @Test
    public void testStringConversion() {
        assertEquals("[E][X] drink tea (at: Nov 20 2019)",
                new Event("drink tea", "2019-11-20").toString());
    }
}