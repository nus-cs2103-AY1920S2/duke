import duke.Event;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void test() {
        Event event = new Event("Major Exam", "2020-04-05");
        String result = event.toString();
        assertEquals("[E][✘] Major Exam (at: 05 Apr 2020)", result);
    }

}
