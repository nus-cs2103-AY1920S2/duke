package liaomeng.duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void stringRepresentation() {
        LocalDate date = LocalDate.parse("2020-02-02");
        Deadline dl = new Deadline(false,"test", date, PriorityLevel.NORMAL);
        assertEquals("[D][Not Done][      ordinary      ] test\n    (by: 2020-02-02, SUNDAY)", dl.toString());
    }
}
