package app.core.commands;

import app.core.UserInterface;
import app.core.tasks.TaskManager;
import app.util.Date;
import app.exceptions.StorageFileException;
import app.exceptions.WrongDateTimeFormatException;
import app.exceptions.WrongUsageException;

final class DeadlineCommand extends Command {
    private String description;
    private Date deadline;

    DeadlineCommand(String args) throws WrongUsageException, WrongDateTimeFormatException {
        String[] splitArgs = args.split("/by");
        if (splitArgs.length != 2) {
            throw new WrongUsageException("Usage: deadline <description> /by <deadline>");
        }

        this.description = splitArgs[0].trim();
        this.deadline = Date.fromFormat(splitArgs[1].trim(), Date.DEFAULT_INPUT_FORMAT);
    }

    @Override
    public void execute(TaskManager taskManager, UserInterface userInterface) throws StorageFileException {
        String output = taskManager.addDeadlineTask(this.description, this.deadline);
        userInterface.render(output);
    }
}