import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class DeadlineTest {
    @Test
    public void testDeadlineObject() {
        Deadline deadline = new Deadline("do your work", LocalDateTime.parse("2020-02-02T20:00"));
        Assertions.assertEquals("[D][x] do your work (by: Feb 2 2020, 8:00 PM)", deadline.toString());
    }

    @Test
    public void testDeadlinePrint() {
        Deadline deadline = new Deadline("do your work", LocalDateTime.parse("2020-02-02T20:00"));
        Assertions.assertEquals("D | 0 | do your work | 2020-02-02T20:00", deadline.toFormatString());
    }
}
