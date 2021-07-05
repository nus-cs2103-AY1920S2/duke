package commands;

import common.Messages;
import data.task.Task;

public class DoneCommand extends Command {

    public static final String COMMAND_WORD = "done";
    public static final String MESSAGE_DONE = "  Nice! I've marked this task as done:  [D] %s";

    public DoneCommand(int toDoneIndex) {
        super(toDoneIndex);
    }

    @Override
    public CommandResult execute() {
        try {
            final Task toDone = getTargetTask();
            toDone.setDone(true);
            return new CommandResult(String.format(MESSAGE_DONE, toDone.getTaskDescription()));
        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
    }

    @Override
    public CommandResult executeForGUI() {
        try {
            final Task toDone = getTargetTask();
            toDone.setDone(true);
            return new CommandResult(String.format(MESSAGE_DONE, toDone.getTaskDescription()));
        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
    }
}
