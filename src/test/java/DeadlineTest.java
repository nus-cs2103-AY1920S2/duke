import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testStringConversion() {
        assertEquals("[D][✗] return book (by: Oct 15 2019)",
                (new Deadline("return book /by 2019-10-15")).toString());
    }
}
