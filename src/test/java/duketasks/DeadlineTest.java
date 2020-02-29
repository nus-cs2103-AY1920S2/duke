package duketasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

public class DeadlineTest {
    @Test
    public void getSaveStringTest() {
        String deadlineDesc = "2103 tutorial";
        LocalDate deadlineBy = LocalDate.parse("2022-01-01");
        String ans = "D|X|2103 tutorial|2022-01-01";
        Deadline test = new Deadline(deadlineDesc, deadlineBy);
        assertEquals(ans, test.getSaveString());
    }
}
