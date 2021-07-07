import org.junit.jupiter.api.Test;
import duke.task.Deadline;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DeadlineTest {

    @Test
    void getTaskIcon() {
        assertEquals("D", new Deadline("Homework", "2012-11-11").getTaskIcon());
    }

    @Test
    void hasTime() {
        assertTrue(new Deadline("Homework", "2012-11-11").hasTime());
    }
}