import duke.commons.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void constructorTest() {
        Deadline deadline = new Deadline("deadline", false, "CS2103T submission", "2020-03-02 2359");
        assertEquals(deadline.toString(), "[ D ] [ ✗ ] CS3203T submission (by: 02 Mar 2020 23:59");
    }
}
