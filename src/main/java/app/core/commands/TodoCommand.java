package app.core.commands;

import app.core.UserInterface;
import app.core.tasks.TaskManager;
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
    public void execute(TaskManager taskManager, UserInterface userInterface) throws StorageFileException {
        String output = taskManager.addTodoTask(this.description);
        userInterface.render(output);
    }
}