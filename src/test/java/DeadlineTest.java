import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void getDateString() {
        LocalDate date = LocalDate.parse("2020-03-19");
        Deadline deadline = new Deadline("name", false, date);
        assertEquals("Mar 19 2020", deadline.getDateString());
    }
}
