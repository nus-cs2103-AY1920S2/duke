package app.core.commands;

import app.core.Messages;
import app.core.tasks.TaskManager;
import app.exceptions.EmptyTaskListException;
import app.util.Pair;

final class ListCommand extends Command {
    @Override
    public Pair execute(TaskManager taskManager) throws EmptyTaskListException {
        String tasks = taskManager.getTasks();
        String output = String.format(Messages.LIST_SUCCESS_MESSAGE, tasks);
        return new Pair(output, false);
    }
}