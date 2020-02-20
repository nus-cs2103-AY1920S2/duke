import duke.commands.Parser;
import duke.tasks.Deadline;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    /**
     * test that output string is correct.
     */
    @Test
    public void toStringTest() {
        Deadline deadline = new Deadline("test", "28-1-2020 1200",
                Parser.PARSER);
        assertEquals("[D][\u2718] test (by: Jan 28 2020 1200PM)", deadline.toString());
    }

    /**
     * test that file data string is correct.
     */
    @Test
    public void fileStringTest() {
        Deadline deadline = new Deadline("test", "28-1-2020 1200",
                Parser.PARSER);
        assertEquals("D | N | test | Jan 28 2020 1200PM", deadline.fileString());
    }
}
