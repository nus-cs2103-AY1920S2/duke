/**
 * Object type of Command is determined by user input
 */
package duke.commands;

import duke.tasks.*;
import duke.storage.Storage;
import duke.exceptions.DukeException;

public abstract class Command {

    protected String command;

    /**
     * Performs an action as requested by user input.
     * @param description Description of task
     * @param tasks   List of current tasks
     * @param storage For storing of tasks into file
     * @throws DukeException If input format is wrong
     */
    public static String execute(String description, TaskList tasks, Storage storage) {
        return null;
    }
}
