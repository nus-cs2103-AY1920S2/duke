package app.core.commands;

import app.core.Messages;
import app.core.tasks.TaskManager;
import app.util.Pair;

final class ByeCommand extends Command {
    @Override
    public Pair execute(TaskManager taskManager) {
        return new Pair(Messages.GOODBYE_MESSAGE, true);
    }
}