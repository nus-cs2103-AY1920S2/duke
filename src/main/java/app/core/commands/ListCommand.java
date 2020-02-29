package app.core.commands;

import app.core.Messages;
import app.core.tasks.TaskManager;
import app.exceptions.EmptyTaskListException;
import app.exceptions.InvalidUsageException;
import app.util.Pair;

final class ListCommand extends Command {
    ListCommand(String args) throws InvalidUsageException {
        if (args.length() != 0) {
            throw new InvalidUsageException(Messages.LIST_WRONG_FORMAT_MESSAGE);
        }
    }

    @Override
    public Pair execute(TaskManager taskManager) throws EmptyTaskListException {
        String tasks = taskManager.getTasks();
        String output = String.format(Messages.LIST_SUCCESS_MESSAGE, tasks);
        return new Pair(output, false);
    }
}