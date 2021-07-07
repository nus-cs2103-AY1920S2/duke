package logic.command;

import commons.Duke;
import commons.Index;
import commons.Messages;
import tasks.Task;
import tasks.TaskList;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the task identified by the index number used in the displayed task list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Noted. I've removed this task:\n\t\t%1$s";
    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
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
        Task taskToDelete = lastShownList.get(targetIndex.getZeroBased());
        taskList.deleteTask(targetIndex.getZeroBased());
        return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, taskToDelete));
    }
}
