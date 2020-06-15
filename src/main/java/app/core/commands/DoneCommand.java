package app.core.commands;

import app.core.Messages;
import app.core.tasks.Task;
import app.core.tasks.TaskManager;
import app.util.Pair;
import app.exceptions.InvalidTaskIndexException;
import app.exceptions.StorageFileException;
import app.exceptions.InvalidUsageException;

final class DoneCommand extends Command {
    private int taskIndex;

    DoneCommand(String args) throws InvalidUsageException {
        try {
            this.taskIndex = Integer.parseInt(args);
        } catch (NumberFormatException e) {
            throw new InvalidUsageException(Messages.DONE_WRONG_FORMAT_MESSAGE);
        }
    }

    @Override
    public Pair execute(TaskManager taskManager) throws InvalidTaskIndexException, StorageFileException {
        try {
            Task task = taskManager.setTaskDone(this.taskIndex);
            String output = String.format(Messages.DONE_SUCCESS_MESSAGE, task);
            return new Pair(output, false);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException(Messages.DONE_INVALID_TASK_INDEX_MESSAGE);
        }
    }
}