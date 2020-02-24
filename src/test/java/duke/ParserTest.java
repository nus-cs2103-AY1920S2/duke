package duke;

import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseInputTest() throws DukeException {
        TaskList dummyList = new TaskList();
        Storage dummyStorage = new Storage("data/test.txt");

        // Expected output
        StringBuilder expected = new StringBuilder();
        expected.append("You've added this Todo task!\n"
                + "[T][\u2718] Borrow book" + "\n"
                + "Now you have " + "1 tasks in the list\n");

        assertEquals(expected.toString(),
                Parser.parse("Todo Borrow book", dummyList, dummyStorage));
    }
}
