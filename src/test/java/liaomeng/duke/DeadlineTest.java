package liaomeng.duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void stringRepresentation() {
        LocalDate date = LocalDate.parse("2020-02-02");
        Deadline dl = new Deadline("test", date);
        assertEquals("[D][Not Done] test (by: 2020-02-02, SUNDAY)", dl.toString());
    }
}
