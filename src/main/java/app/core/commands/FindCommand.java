package app.core.commands;

import app.core.Messages;
import app.core.tasks.TaskManager;
import app.util.Pair;
import app.exceptions.EmptyTaskListException;
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
    public Pair execute(TaskManager taskManager) throws EmptyTaskListException {
        String tasks = taskManager.findMatchingTasks(this.toMatch);
        String output = String.format(Messages.FIND_SUCCESS_MESSAGE, tasks);
        return new Pair(output, false);
    }
}