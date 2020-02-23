package duke;

import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseInputTest() {
        TaskList dummyList = new TaskList();
        Storage dummyStorage = new Storage("");
        /*
        try {
            assertEquals("Your command is not recognized", Parser.parse("Hello World", dummyList, dummyStorage));
        } catch (DukeException e) {
            e.printStackTrace();
        }

         */
    }
}
