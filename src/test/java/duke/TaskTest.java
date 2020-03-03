package duke;

import duke.task.Deadline;
import duke.task.EventObj;
import duke.task.Task;
import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void printDateTimeTest() {
        String desc = "project meeting";
        String datetime = "2020-01-15";
        Task t = new EventObj(desc, datetime);
        Task t2 = new Deadline(desc, datetime);
        assert (t.printDateTime().equals("Jan 15 2020")
                && t2.printDateTime().equals("Jan 15 2020"));


    }
}
