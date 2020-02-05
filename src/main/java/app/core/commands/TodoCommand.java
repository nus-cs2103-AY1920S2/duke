package app.core.commands;

import app.core.tasks.TaskManager;
import app.util.Pair;
import app.exceptions.StorageFileException;
import app.exceptions.InvalidUsageException;

final class TodoCommand extends Command {
    private String description;

    TodoCommand(String args) throws InvalidUsageException {
        if (args.equals("")) {
            throw new InvalidUsageException("Usage: todo <description>");
        }

        this.description = args;
    }

    @Override
    public Pair execute(TaskManager taskManager) throws StorageFileException {
        String output = taskManager.addTodoTask(this.description);
        return new Pair(output, false);
    }
}