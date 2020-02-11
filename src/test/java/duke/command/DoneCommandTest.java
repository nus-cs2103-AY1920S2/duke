package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import duke.DukeException;
import duke.command.Command.ExecuteResult;
import duke.task.Task;
import duke.task.Todo;

public class DoneCommandTest {
    @Test
    public void execute_indexOutOfBounds_exceptionThrown() {
        final int DONE_INDEX = 500;
        final List<Task> EMPTY_TASK_LIST = new ArrayList<>();

        DoneCommand doneCommand = new DoneCommand(DONE_INDEX);
        
        assertThrows(DukeException.class, () -> doneCommand.execute(EMPTY_TASK_LIST));
    }

    @Test
    public void execute_normal_workCorrectly() throws DukeException {
        final int DONE_INDEX = 0;
        final String TODO_TITLE = "Complete Level 9";
        final Todo TODO = new Todo(TODO_TITLE);
        final List<Task> TASK_LIST = List.of(TODO);

        final List<Task> EXPECTED_TASK_LIST = List.of(TODO.setDone(true));
        final String EXPECTED_MESSAGE =
                "Nice! I've marked this task as done:\n"
                + "  [T][\u2713] " + TODO_TITLE; // \u2713 = tick

        DoneCommand doneCommand = new DoneCommand(DONE_INDEX);
        ExecuteResult result = doneCommand.execute(TASK_LIST);

        assertEquals(EXPECTED_TASK_LIST, result.getTasks());
        assertEquals(EXPECTED_MESSAGE, result.getMessage());
    }
}