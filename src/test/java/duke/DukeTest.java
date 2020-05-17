package duke;

import duke.parser.Parser;
import duke.tasks.Deadline;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void createDeadlineTest() {

        Duke duke = new Duke();
        String expected = "Patrick, I don't recognize this command :(\n"
                + "Type 'help' if you need help!\n";

        assertEquals(expected, duke.getResponse("Hello World"));
    }
}
