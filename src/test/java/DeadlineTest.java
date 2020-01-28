import org.junit.jupiter.api.Test;
import task.Deadline;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void toStringTest() {
        Deadline dl = new Deadline("finish CS2103T", LocalDate.parse("2020-01-29"));
        assertEquals("[D]\u2718 finish CS2103T (by: Jan 29 2020)", dl.toString());
    }

    @Test
    public void isDoneTest() {
        Deadline dl = new Deadline("finish CS2103T", LocalDate.parse("2020-01-29"));
        dl.taskDone();
        assertEquals("[D]\u2713 finish CS2103T (by: Jan 29 2020)", dl.toString());
    }
}
