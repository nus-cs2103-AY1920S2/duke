package duke;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testDeadline() {
        LocalDate localDate = LocalDate.parse("2020-02-04");
        Deadline deadline = new Deadline("newDeadline", localDate);
        assertEquals("[D][N] newDeadline (by: Feb 4 2020)", deadline.toString());
    }
}
