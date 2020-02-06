import org.junit.jupiter.api.Test;
import task.Deadline;

import java.time.LocalDate;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void getTypeIcon_deadlineTask_hasCorrectTypeIcon() {
        String expected = "D";
        Deadline deadline = new Deadline("read book", LocalDate.of(2020, 1, 1));
        assertEquals(expected, deadline.getTypeIcon());
    }

}

