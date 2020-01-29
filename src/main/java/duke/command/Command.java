package duke.command;

import duke.ui.Ui;
import duke.task.Storage;
import duke.task.TaskList;

/**
 * Represents a Command.
 * This is an abstract class meant as a template for the commands.
 */
public abstract class Command {
    /**
     * Abstract method to execute the command.
     * Must be overridden.
     *
     * @param tasks TaskList object that contains the tasks of the application.
     * @param ui Ui object for the command to interact with the user.
     * @param storage storage object for the retrieval/saving of tasks.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}
