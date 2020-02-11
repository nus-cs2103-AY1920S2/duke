package commands;

import dukeexception.DukeException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

import java.io.IOException;

/**
 * Creates a new Command object according to user input.
 */
public abstract class Command {

    /**
     * Execute the respective command.
     *
     * @param tasks This is the TaskList being loaded.
     * @param ui This is to interact with the user to see the output.
     * @param storage This allows for TaskList to be updated and stored in hard drive.
     * @throws DukeException depending on command, different child class exception is thrown.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException;

    /**
     * Check for Exit Command and exit programme if returns false.
     *
     * @return boolean This checks if command is Exit Command.
     */
    public abstract boolean isExit();

}
