package duke.command;

import duke.core.Ui;
import duke.core.Storage;
import duke.task.TaskList;

import duke.exception.DukeException;

/**
 * Abstract class that represents a Command in Duke.
 */
public abstract class Command {
    String input;

    /**
     * Constructs a fresh instance of a Command.
     * @param input User input for the command.
     */
    public Command(String input) {
        this.input = input;
    }

    /**
     * Abstract method for the command to execute.
     * @param tasks Tasklist for Duke.
     * @param ui Ui for Duke.
     * @param storage Storage for Duke
     * @return A String containing the response from the executed method.
     * @throws DukeException Throws any DukeException to the Duke class to be handled.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}