package app.core.commands;

import app.core.UserInterface;
import app.core.tasks.TaskManager;

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
    public void execute(TaskManager taskManager, UserInterface userInterface) throws InvalidTaskIndexException, StorageFileException {
        try {
            String output = taskManager.deleteTask(this.taskIndex);
            userInterface.render(output);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException(
                "Invalid task index. Please refer to the 'list' command for available indices."
            );
        }
    }
}