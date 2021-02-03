import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

public class DeadlineTaskTest {

    @Test 
    public void testStringConversion() {
        assertEquals("[D][\u2718] return book (by:Oct 10 2019)", new DeadlineTask("return book", LocalDate.parse("2019-10-10")).toString());
    }
}