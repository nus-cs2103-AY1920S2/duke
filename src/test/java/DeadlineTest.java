
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testStringConvert() {
        assertEquals("[D][O] read book (By: JAN 1 2020)",
                new Deadlines("read book",false, "2020-01-01").toString());
        assertEquals("[D][X] read book (By: JAN 1 2020)",
                new Deadlines("read book", true, "2020-01-01").toString());
    }

    @Test
    public void testGetDeadline() {
        assertEquals("2020-01-01",
                new Deadlines("read book", true, "2020-01-01").getDeadline());
    }
}
