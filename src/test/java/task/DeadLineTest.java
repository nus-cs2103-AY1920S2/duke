import org.junit.jupiter.api.Test;

import duke.task.Deadline;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadLineTest {

    @Test
    public void testStringConversion() {
        assertEquals("[D][✗] hello world (by: 02 Dec 2012)", (new Deadline("hello world", false, "2012/12/02")).toString());
    }
}