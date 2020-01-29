package duke.task;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class DeadlineTest {

    @Test
    void toStringTest(){
        Deadline deadline = new Deadline("project meeting ", LocalDate.parse("2020-12-12"));
        String expectedFirst = "[D][Not Done] project meeting (by: Dec 12 2020)";
        String actualFirst = deadline.toString();
        assertEquals(expectedFirst, actualFirst);
    }

    @Test
    void toStringTasksTest(){
        Deadline deadline = new Deadline("project meeting ", LocalDate.parse("2020-12-12"));
        String expectedSec = "D/0/project meeting /2020-12-12\n";
        String actualSec = deadline.toStringTasks();
        assertEquals(expectedSec, actualSec);
    }
}
