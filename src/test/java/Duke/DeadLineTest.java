package duke;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.time.LocalDateTime;

public class DeadLineTest {
    @Test
    public void testDeadlineObject() {
        DeadLine deadline = new DeadLine("return library books", LocalDateTime.parse("2019-02-05T20:00"));
        Assertions.assertEquals("[D][" + "\u2718" + "]"+  " return library books (by: Feb 5 2019, 8:00 pm)", deadline.toString());
    }

    @Test
    public void testDeadlinePrint() {
        DeadLine deadline = new DeadLine("return library books", LocalDateTime.parse("2019-02-05T20:00"));
        Assertions.assertEquals("D  | 0 | return library books | 2019-02-05T20:00", deadline.toFileString());
    }
}
