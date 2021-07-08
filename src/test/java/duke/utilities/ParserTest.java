package duke.utilities;
import duke.tasks.Event;
import duke.tasks.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void testTaskParsing() {
        Parser parser = new Parser();
        Task task = new Event("1", "blah", "9/02/2020");
        assertEquals("E|1|blah|2020-02-09", parser.parseTask(task));
    }
}
