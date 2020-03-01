package logic.command;

import commons.Duke;
import commons.Index;
import commons.Messages;
import tasks.Task;
import tasks.TaskList;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class DoneCommand implements Command {

    public static final String COMMAND_WORD = "done";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the task identified by the index number used in the displayed task list as done.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DONE_TASK_SUCCESS = "\"Nice! I've marked this task as done:" +
            "\n\tTask marked as done %1$s";

    private final Index targetIndex;

    public DoneCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Duke duke) throws CommandException {
        requireNonNull(duke);
        TaskList taskList = duke.getTaskList();
        List<Task> lastShownList = taskList.getTaskList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
        Task taskMarkedAsDone = lastShownList.get(targetIndex.getZeroBased());
        taskList.markAsDone(targetIndex.getZeroBased());
        return new CommandResult(String.format(MESSAGE_DONE_TASK_SUCCESS, taskMarkedAsDone));
    }
}
