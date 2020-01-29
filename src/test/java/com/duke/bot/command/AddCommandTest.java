package com.duke.bot.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import com.duke.bot.TaskList;
import com.duke.bot.command.Command.ExecuteResult;
import com.duke.bot.task.Todo;
import org.junit.jupiter.api.Test;

public class AddCommandTest {
    @Test
    public void execute_emptyTaskList() {
        final String TODO_TITLE = "Complete Level 9";
        final Todo TODO = new Todo(TODO_TITLE);
        final TaskList EMPTY_TASK_LIST = new TaskList();

        final TaskList EXPECTED_TASK_LIST = new TaskList(List.of(TODO));
        final List<String> EXPECTED_MESSAGES = List.of(
                "Got it. I've added this task:",
                "  [T][\u2717] " + TODO_TITLE,
                "Now you have 1 tasks in the list."
        );

        AddCommand addCommand = new AddCommand(TODO);
        ExecuteResult result = addCommand.execute(EMPTY_TASK_LIST);

        assertEquals(EXPECTED_TASK_LIST.getUnderlyingList(), result.getTasks().getUnderlyingList());
        assertEquals(EXPECTED_MESSAGES, result.getMessages());
    }
}