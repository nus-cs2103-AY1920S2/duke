package duke.commands;

import duke.ui.Ui;
import duke.tasks.TaskList;
import duke.exceptions.DukeException;

/**
 * Represents a command that can be run by Duke.
 */
public interface Command {
    /**
     * Executes the <code>Command</code>.
     * @param arg   Arguments for the command (may be empty).
     * @param tasks List of Tasks.
     * @param ui    User interface for Duke.
     * @throws DukeException If inputs are invalid.
     */
    void execute(String arg, TaskList tasks, Ui ui) throws DukeException;
}
