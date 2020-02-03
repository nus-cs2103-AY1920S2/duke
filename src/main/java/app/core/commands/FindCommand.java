package app.core.commands;

import app.core.tasks.TaskManager;
import app.core.UserInterface;
import app.exceptions.WrongUsageException;

public class FindCommand extends Command {
    private String toMatch;

    public FindCommand(String args) throws WrongUsageException {
        if (args.equals("")) {
            throw new WrongUsageException("Usage: find <string_to_match>");
        }

        this.toMatch = args;
    }

    @Override
    public void execute(TaskManager taskManager, UserInterface userInterface) {
        String output = taskManager.findMatchingTasks(this.toMatch);
        userInterface.render(output);
    }
}