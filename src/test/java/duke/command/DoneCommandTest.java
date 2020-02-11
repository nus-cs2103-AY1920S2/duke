package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.List;
import org.junit.jupiter.api.Test;
import duke.DukeException;
import duke.TaskList;
import duke.command.Command.ExecuteResult;
import duke.task.Todo;

public class DoneCommandTest {
    @Test
    public void execute_indexOutOfBounds_exceptionThrown() {
        final int DONE_INDEX = 500;
        final TaskList EMPTY_TASK_LIST = new TaskList();

        DoneCommand doneCommand = new DoneCommand(DONE_INDEX);
        
        assertThrows(DukeException.class, () -> doneCommand.execute(EMPTY_TASK_LIST));
    }

    @Test
    public void execute_normal_workCorrectly() throws DukeException {
        final int DONE_INDEX = 0;
        final String TODO_TITLE = "Complete Level 9";
        final Todo TODO = new Todo(TODO_TITLE);
        final TaskList TASK_LIST = new TaskList(List.of(TODO));

        final TaskList EXPECTED_TASK_LIST = new TaskList(List.of(TODO.setDone(true)));
        final String EXPECTED_MESSAGE =
                "Nice! I've marked this task as done:\n"
                + "  [T][\u2713] " + TODO_TITLE; // \u2713 = tick

        DoneCommand doneCommand = new DoneCommand(DONE_INDEX);
        ExecuteResult result = doneCommand.execute(TASK_LIST);

        assertEquals(EXPECTED_TASK_LIST.getUnderlyingList(), result.getTasks().getUnderlyingList());
        assertEquals(EXPECTED_MESSAGE, result.getMessage());
    }
}