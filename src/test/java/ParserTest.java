import duke.TaskList;
import duke.exception.DukeException;
import duke.interact.Parser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void newDeadlineTask_noDetails_DukeExceptionThrown() {
//        try {
//            assertEquals(0, new Parser().parse("deadline", new TaskList()));
//        } catch (DukeException e) {
//            assertEquals("    ____________________________________________________________\n"
//                    + "     ☹ OOPS!!! The description of a deadline cannot be empty.\n"
//                    + "    ____________________________________________________________\n", e.toString());
//        }
    }
}
