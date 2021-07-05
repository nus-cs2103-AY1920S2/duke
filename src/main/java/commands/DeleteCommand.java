package commands;

import common.Messages;
import data.exceptions.TaskNotFoundException;
import data.task.Task;

/**
 * Deletes a task identified using it's last displayed index from the task list.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE_1 = COMMAND_WORD
            + ": Deletes the task by the index number.";
            ;
    public static final String MESSAGE_USAGE_2 = "    Parameters: INDEX";
    public static final String MESSAGE_USAGE_3 = "    Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Deleted Task: %1$s";

    public DeleteCommand(int targetVisibleIndex) {

        super(targetVisibleIndex);
    }

    @Override
    public CommandResult execute() {
        try {
            final Task target = getTargetTask();
            taskManager.removeTask(target);
            return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, target.getTaskDescription()));
        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        } catch (TaskNotFoundException tnfe) {
            return new CommandResult(Messages.MESSAGE_TASK_NOT_IN_TASKLIST);
        }
    }

    @Override
    public CommandResult executeForGUI() {
        try {
            final Task target = getTargetTask();
            taskManager.removeTask(target);
            return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, target.getTaskDescription()));
        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        } catch (TaskNotFoundException tnfe) {
            return new CommandResult(Messages.MESSAGE_TASK_NOT_IN_TASKLIST);
        }
    }

}
