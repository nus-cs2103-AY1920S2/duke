package app.core.commands;

import app.core.tasks.TaskManager;
import app.util.Date;
import app.util.Pair;
import app.exceptions.StorageFileException;
import app.exceptions.InvalidDateTimeFormatException;
import app.exceptions.InvalidUsageException;

final class DeadlineCommand extends Command {
    private String description;
    private Date deadline;

    DeadlineCommand(String args) throws InvalidUsageException, InvalidDateTimeFormatException {
        String[] splitArgs = args.split("/by");
        if (splitArgs.length != 2) {
            throw new InvalidUsageException("Usage: deadline <description> /by <deadline>");
        }

        this.description = splitArgs[0].trim();
        this.deadline = Date.fromFormat(splitArgs[1].trim(), Date.DEFAULT_INPUT_FORMAT);
    }

    @Override
    public Pair execute(TaskManager taskManager) throws StorageFileException {
        String output = taskManager.addDeadlineTask(this.description, this.deadline);
        return new Pair(output, false);
    }
}