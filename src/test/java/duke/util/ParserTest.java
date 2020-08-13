package duke.util;

import duke.commands.DeadlineCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void testParseCommand_validCommand_success() throws DukeException {
        assertEquals(Parser.parseCommand("done 2"), new DoneCommand(2));
        assertEquals(Parser.parseCommand("event school dance /at 2020-02-01 1800"),
                new EventCommand("school dance", "2020-02-01 1800"));
        assertEquals(Parser.parseCommand("deadline do work /by 2020-03-05 1800"),
                new DeadlineCommand("do work", "2020-03-05 1800"));
    }

    @Test
    public void testParseCommand_doneNegativeIndex_exceptionThrown() {
        try {
            String t1 = "done -1";
            assertEquals(Parser.parseCommand(t1), new DoneCommand(-1));
            fail();
        } catch (DukeException e) {
            assertEquals(Ui.setBorder("You can't enter a negative index!!"), e.toString());
        }
    }

    @Test
    public void testParseFileLine_validLine_success() throws DukeException {
        Event e1 = new Event("school dance", "2020-01-20 1800");
        final ToDo e2 = new ToDo("eat food");
        Deadline e3 = new Deadline("study", "2020-02-18 1700");
        e1.setDone();
        e3.setDone();
        assertEquals(e1, Parser.parseFileLine("[E][X] school dance /at 2020-01-20 1800"));
        assertEquals(e2, Parser.parseFileLine("[T][ ] eat food"));
        assertEquals(e3, Parser.parseFileLine("[D][X] study /by 2020-02-18 1700"));
    }

    /*
    To Do:
    testParseCommand_invalidCommand_exceptionThrown()
    testParseCommand_ToDoNoDescription_exceptionThrown()

    testParseCommand_deleteNoIndex_exceptionThrown()
    testParseCommand_deleteNegativeIndex_exceptionThrown()
    testParseCommand_deleteNonexistentIndex_exceptionThrown()

    testParseCommand_eventNoDescription_exceptionThrown()
    testParseCommand_eventNoDate_exceptionThrown()
    testParseCommand_eventInvalidDateTime_exceptionThrown()

    testParseCommand_deadlineNoDescription_exceptionThrown()
    testParseCommand_deadlineNoDate_exceptionThrown()
    testParseCommand_deadlineInvalidDateTime_exceptionThrown()

    testParseFileLine_invalidLine_exceptionThrown()
    testParseFileLine_validLine_success()

    isValidIndex_nonexistentIndex_exceptionThrown()
    isValidIndex_validIndex_success()

     */

}
