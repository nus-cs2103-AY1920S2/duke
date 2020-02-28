import duke.TaskList;
import duke.command.Command;
import duke.command.CommandAdd;
import duke.exception.DukeException;
import duke.exception.DukeExceptionDate;
import duke.exception.DukeExceptionDateFormat;
import duke.exception.DukeExceptionDescription;
import duke.interact.Parser;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void newDeadlineTask_noDescription_exceptionThrown() {
        Exception expectedException = new DukeExceptionDescription("deadline");
        try {
            assertEquals(0, new Parser().parse("deadline", new TaskList()));
        } catch (DukeException e) {
            assertEquals(expectedException.toString(), e.toString());
        }
    }

    @Test
    public void newDeadlineTask_noDate_exceptionThrown() {
        Exception expectedException = new DukeExceptionDate("deadline");
        try {
            assertEquals(0, new Parser().parse("deadline prune leaves", new TaskList()));
        } catch (DukeException e) {
            assertEquals(expectedException.toString(), e.toString());
        }
    }

    @Test
    public void newDeadlineTask_wrongDateFormat_exceptionThrown() {
        Exception expectedException = new DukeExceptionDateFormat();
        try {
            assertEquals(0, new Parser().parse("deadline prune leaves /by 19-09-2200", new TaskList()));
        } catch (DukeException e) {
            assertEquals(expectedException.toString(), e.toString());
        }
    }

    @Test
    public void newTasks_correctFormat() {
        Parser parser = new Parser();
        LocalDate expectedDate = LocalDate.parse("2020-02-21");

        Task expectedTodo = new ToDo(" buy a flower pot");
        Command parsedTodo = parser.parse("todo buy a flower pot", new TaskList());
        CommandAdd todoCasted = (CommandAdd) parsedTodo;
        assertEquals(expectedTodo.toString(), todoCasted.getTask().toString());

        Task expectedEvent = new Event(" photosynthesize", expectedDate);
        Command parsedEvent = parser.parse("event photosynthesize /at 2020-02-21", new TaskList());
        CommandAdd eventCasted = (CommandAdd) parsedEvent;
        assertEquals(expectedEvent.toString(), eventCasted.getTask().toString());

        Task expectedDeadline = new Event(" grow tree", expectedDate);
        Command parsedDeadline = parser.parse("deadline grow tree /by 2020-02-21", new TaskList());
        CommandAdd deadlineCasted = (CommandAdd) parsedEvent;
        assertEquals(expectedEvent.toString(), deadlineCasted.getTask().toString());
    }
}
