package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Test;
import duke.TaskList;
import duke.command.Command.ExecuteResult;
import duke.task.Todo;

public class AddCommandTest {
    @Test
    public void execute_emptyTaskList() {
        final String TODO_TITLE = "Complete Level 9";
        final Todo TODO = new Todo(TODO_TITLE);
        final TaskList EMPTY_TASK_LIST = new TaskList();

        final TaskList EXPECTED_TASK_LIST = new TaskList(List.of(TODO));
        final String EXPECTED_MESSAGE =
                "Got it. I've added this task:\n"
                + "  [T][\u2717] " + TODO_TITLE + "\n" // \u2717 = cross
                + "Now you have 1 tasks in the list.";

        AddCommand addCommand = new AddCommand(TODO);
        ExecuteResult result = addCommand.execute(EMPTY_TASK_LIST);

        assertEquals(EXPECTED_TASK_LIST.getUnderlyingList(), result.getTasks().getUnderlyingList());
        assertEquals(EXPECTED_MESSAGE, result.getMessage());
    }
}