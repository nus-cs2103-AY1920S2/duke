package app.core.commands;

import app.core.Messages;
import app.core.tasks.TaskManager;
import app.util.Pair;
import app.exceptions.InvalidUsageException;

final class FindCommand extends Command {
    private String toMatch;

    FindCommand(String args) throws InvalidUsageException {
        if (args.equals("")) {
            throw new InvalidUsageException(Messages.FIND_WRONG_FORMAT_MESSAGE);
        }

        this.toMatch = args;
    }

    @Override
    public Pair execute(TaskManager taskManager) {
        String output = taskManager.findMatchingTasks(this.toMatch);
        return new Pair(output, false);
    }
}