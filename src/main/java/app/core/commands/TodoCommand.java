package app.core.commands;

import app.core.UserInterface;
import app.core.tasks.TaskManager;

import app.exceptions.WrongUsageException;

public class TodoCommand extends Command {
    private String description;

    public TodoCommand(String args) throws WrongUsageException {
        if (args.equals("")) {
            throw new WrongUsageException("Usage: todo <description>");
        }

        this.description = args;
    }

    @Override
    public void execute(TaskManager taskManager, UserInterface userInterface) {
        String output = taskManager.addTodoTask(this.description);
        userInterface.render(output);
    }
}