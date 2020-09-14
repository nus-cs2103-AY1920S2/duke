package dukeproj.command;

import dukeproj.Storage;
import dukeproj.Ui;
import dukeproj.data.Schedule;
import dukeproj.data.TaskList;
import dukeproj.exception.BadDateException;
import dukeproj.exception.BadDescriptionException;
import dukeproj.exception.DukeDescriptionException;

/**
 * Represents an abstract class command which serves as the parent class to all command sub-classes.
 */
public abstract class Command {

    public abstract String execute(Ui ui, TaskList taskList, Storage storage, Schedule schedule)
            throws BadDescriptionException, DukeDescriptionException, BadDateException;

    /**
     * Checks if the command is an exit command.
     * This method will always return false unless the object is an ExitCommand.
     *
     * @return False as default.
     */
    public boolean isExit() {
        return false;
    }
}
