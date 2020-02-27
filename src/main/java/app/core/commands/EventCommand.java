package app.core.commands;

import app.core.Messages;
import app.core.tasks.TaskManager;
import app.util.Date;
import app.util.Pair;
import app.exceptions.StorageFileException;
import app.exceptions.InvalidDateTimeFormatException;
import app.exceptions.InvalidUsageException;

final class EventCommand extends Command {
    private String description;
    private Date when;

    EventCommand(String args) throws InvalidUsageException, InvalidDateTimeFormatException {
        String[] splitArgs = args.split("/at");
        if (splitArgs.length != 2) {
            throw new InvalidUsageException(Messages.EVENT_WRONG_FORMAT_MESSAGE);
        }

        assert splitArgs.length == 2 : "There should only be 2 arguments in the split arguments";

        this.description = splitArgs[0].trim();
        this.when = Date.fromFormat(splitArgs[1].trim(), Date.DEFAULT_INPUT_FORMAT);
    }

    @Override
    public Pair execute(TaskManager taskManager) throws StorageFileException {
        String output = taskManager.addEventTask(this.description, this.when);
        return new Pair(output, false);
    }
}