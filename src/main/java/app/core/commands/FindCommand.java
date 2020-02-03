package app.core.commands;

import app.core.tasks.TaskManager;
import app.core.UserInterface;
import app.exceptions.InvalidUsageException;

final class FindCommand extends Command {
    private String toMatch;

    FindCommand(String args) throws InvalidUsageException {
        if (args.equals("")) {
            throw new InvalidUsageException("Usage: find <string_to_match>");
        }

        this.toMatch = args;
    }

    @Override
    public void execute(TaskManager taskManager, UserInterface userInterface) {
        String output = taskManager.findMatchingTasks(this.toMatch);
        userInterface.render(output);
    }
}