package lcduke;

import lcduke.Task;
import lcduke.Deadline;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void TestToStringOutput() {
        Task task1 = new Deadline( "description", "2019-08-01");
        assertEquals("[D]" + super.toString() + " (by: "
                        + "Aug 1 2019" + ")", task1.toString());
    }
}
