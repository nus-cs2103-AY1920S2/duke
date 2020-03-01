package logic.command;

import commons.Duke;
import tasks.TaskList;

import static java.util.Objects.requireNonNull;

public class TagCommand implements Command {
    public static final String COMMAND_WORD = "tag";
    public static final String MESSAGE_SUCCESS = "Here are the tasks in your list containing %s";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tasks whose name contain any of "
            + "the specified tag (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: TAGS [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " cs2103t cs2101";
    public static final String NO_TAGS = "No tasks with this tag found :o";

    private final String tag;

    public TagCommand(String tag) {
        this.tag = tag;
    }

    @Override
    public CommandResult execute(Duke duke) {
        requireNonNull(duke);
        TaskList taskList = duke.getTaskList().findTaskContainingTag(tag);
        if (taskList.isEmpty()) {
            return new CommandResult(NO_TAGS);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, tag) +
                taskList.printList());
    }
}
