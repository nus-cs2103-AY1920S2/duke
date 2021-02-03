import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

public class EventTaskTest {

    @Test 
    public void testStringConversion() {
        assertEquals("[E][\u2718] team meeting (at:Oct 10 2019)", new EventTask("team meeting", LocalDate.parse("2019-10-10")).toString());
    }
}