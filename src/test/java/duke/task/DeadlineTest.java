package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void setTestDeadlineWithoutTime() {
        assertEquals("[D][X] Read book (by: 1 Feb 2020)",
                new Deadline("Read book", LocalDate.parse("2020-02-01")).toString());
    }

    @Test
    public void setTestDeadlineWithTime() {
        assertEquals("[D][X] Finish homework (by: 2 Feb 2020, 18:00)",
                new Deadline("Finish homework", LocalDate.parse("2020-02-02"),
                        LocalTime.parse("18:00")).toString());
    }

}
