package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void setTestEventWithoutTime() {
        assertEquals("[E][X] Read book (at: 1 Feb 2020)",
                new Event("Read book", LocalDate.parse("2020-02-01")).toString());
    }

    @Test
    public void setTestEventWithStartTime() {
        assertEquals("[E][X] Finish homework (at: 2 Feb 2020, 18:00)",
                new Event("Finish homework", LocalDate.parse("2020-02-02"),
                        LocalTime.parse("18:00")).toString());
    }

    @Test
    public void setTestEventWithStartAndEndTime() {
        assertEquals("[E][X] Finish homework (at: 2 Feb 2020, 18:00 to 19:00)",
                new Event("Finish homework", LocalDate.parse("2020-02-02"),
                        LocalTime.parse("18:00"), LocalTime.parse("19:00")).toString());
    }
}
