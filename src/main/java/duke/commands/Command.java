package duke.commands;

import duke.parsers.CommandParser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.io.IOException;

import duke.exceptions.DukeException;

/**
 * Represents a command that can be run by Duke.
 */
public abstract class Command {
    protected CommandParser commandParser;

    /**
     * Creates a Command object.
     * 
     * @param commandParser CommandParser to help parse arguments.
     */
    public Command(CommandParser commandParser) {
        this.commandParser = commandParser;
    }

    /**
     * Saves the TaskList to Storage.
     * 
     * @param storage The storage medium.
     * @param tasks The in-memory list of tasks.
     */
    protected void save(Storage storage, TaskList tasks) throws DukeException {
        try {
            storage.save(tasks.getAllTasks());
        } catch (IOException e) {
            throw new DukeException("Error when saving to disk!");
        }
    }

    /**
     * Separates a given string by a the first occurrence of a delimiter.
     * 
     * @param str       String to be split.
     * @param delimiter Delimiter that divides the string.
     * @return Array of the two halves of the given string split by the delimiter,
     *         or array of the original string if delimiter is not present.
     */
    protected String[] split(String str, String delimiter) {
        return commandParser.splitByDelimiter(str, delimiter);
    }

    /**
     * Executes the given command based on the argument string.
     * 
     * @param arg     Argument string for the command (may be empty).
     * @param tasks   List of Tasks.
     * @param ui      User interface for Duke.
     * @param storage Storage for Duke.
     * @throws DukeException If inputs are invalid.
     */
    abstract void execute(String arg, TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
