package app.core.commands;

import app.core.tasks.TaskManager;
import app.util.Pair;

final class ListCommand extends Command {
    @Override
    public Pair execute(TaskManager taskManager) {
        String output = taskManager.toString();
        return new Pair(output, false);
    }
}