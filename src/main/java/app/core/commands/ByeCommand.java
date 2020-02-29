package app.core.commands;

import app.core.Messages;
import app.core.tasks.TaskManager;
import app.exceptions.InvalidUsageException;
import app.util.Pair;

final class ByeCommand extends Command {
    ByeCommand(String args) throws InvalidUsageException {
        if (args.length() != 0) {
            throw new InvalidUsageException(Messages.BYE_WRONG_FORMAT_MESSAGE);
        }
    }

    @Override
    public Pair execute(TaskManager taskManager) {
        return new Pair(Messages.GOODBYE_MESSAGE, true);
    }
}