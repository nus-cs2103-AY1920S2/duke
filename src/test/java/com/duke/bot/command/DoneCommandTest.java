package com.duke.bot.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.List;
import com.duke.bot.DukeException;
import com.duke.bot.TaskList;
import com.duke.bot.command.Command.ExecuteResult;
import com.duke.bot.task.Todo;
import org.junit.jupiter.api.Test;

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
        final List<String> EXPECTED_MESSAGES = List.of(
                "Nice! I've marked this task as done:",
                "  [T][\u2713] " + TODO_TITLE
        );

        DoneCommand doneCommand = new DoneCommand(DONE_INDEX);
        ExecuteResult result = doneCommand.execute(TASK_LIST);

        assertEquals(EXPECTED_TASK_LIST.getUnderlyingList(), result.getTasks().getUnderlyingList());
        assertEquals(EXPECTED_MESSAGES, result.getMessages());
    }
}