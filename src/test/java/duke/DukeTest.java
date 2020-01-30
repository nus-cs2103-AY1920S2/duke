package duke;

import duke.exception.InvalidIndexException;
import duke.exception.MissingTimeException;
import duke.exception.TimeFormatException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DukeTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }
    
    @Test
    public void handleDone_exceptionThrown() {
        Duke duke = new Duke();
        assertThrows(InvalidIndexException.class, () -> duke.handleDone("abc"));
    }

    @Test
    public void handleEvent_exceptionThrown() {
        Duke duke = new Duke();
        assertThrows(MissingTimeException.class, () -> duke.handleEvent("my event"));
        assertThrows(TimeFormatException.class, () -> duke.handleEvent("my event /at 1998/03/01 13"));
    }

    @Test
    public void parserTests() {
        new ParserTest().stringToTimeTest();
    }
}
