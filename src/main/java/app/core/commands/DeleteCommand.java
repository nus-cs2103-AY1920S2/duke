package app.core.commands;

import app.core.Messages;
import app.core.tasks.Task;
import app.core.tasks.TaskManager;
import app.util.Pair;
import app.exceptions.InvalidTaskIndexException;
import app.exceptions.StorageFileException;
import app.exceptions.InvalidUsageException;

final class DeleteCommand extends Command {
    private int taskIndex;

    DeleteCommand(String args) throws InvalidUsageException {
        try {
            this.taskIndex = Integer.parseInt(args);
        } catch (NumberFormatException e) {
            throw new InvalidUsageException(Messages.DELETE_WRONG_FORMAT_MESSAGE);
        }
    }

    @Override
    public Pair execute(TaskManager taskManager) throws InvalidTaskIndexException, StorageFileException {
        try {
            Task task = taskManager.deleteTask(this.taskIndex);
            String output = String.format(Messages.DELETE_TASK_SUCCESS_MESSAGE, task, taskManager.getSize());
            return new Pair(output, false);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException(Messages.DELETE_INVALID_TASK_INDEX_MESSAGE);
        }
    }
}