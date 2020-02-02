
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testStringConversion() {
        assertEquals("[D][✗]test deadline (by: 2020-02-02)", new Deadline("test deadline", LocalDate.parse("2020-02-02")).toString());
    }

    @Test
    public void testGetBy() {
        assertEquals(LocalDate.parse("2020-02-02"), new Deadline("test deadline", LocalDate.parse("2020-02-02")).getBy());
    }


}
