package logic.command;

import commons.Duke;
import commons.Messages;
import tasks.TaskList;

import java.awt.event.KeyEvent;

import static java.util.Objects.requireNonNull;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_SUCCESS = "Here are the tasks in your list containing %s";
    public static final String NO_TASKS = "No tasks with this keyword found :o";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tasks whose name contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " cs2103t cs2101";

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public CommandResult execute(Duke duke) {
        requireNonNull(duke);
        TaskList taskList = duke.getTaskList().findTaskContainingKeyword(keyword);
        if (taskList.isEmpty()) {
            return new CommandResult(NO_TASKS);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, keyword)
                + taskList.printList());
    }
}
