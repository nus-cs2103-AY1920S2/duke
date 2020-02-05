package app.core.commands;

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
            throw new InvalidUsageException("Usage: delete <task_index>");
        }
    }

    @Override
    public Pair execute(TaskManager taskManager) throws InvalidTaskIndexException, StorageFileException {
        try {
            String output = taskManager.deleteTask(this.taskIndex);
            return new Pair(output, false);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException(
                "Invalid task index. Please refer to the 'list' command for available indices."
            );
        }
    }
}