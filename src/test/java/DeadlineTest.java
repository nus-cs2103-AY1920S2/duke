import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {

    @Test
    public void testStringConversion() {
        assertEquals("[D][X] drink coffee (by: Oct 15 2019)",
                new Deadline("drink coffee", "2019-10-15").toString());
    }
}