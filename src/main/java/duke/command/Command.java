package duke.command;

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
     * @param storage storage object for the retrieval/saving of tasks.
     * @return The program's output.
     */
    public abstract String execute(TaskList tasks, Storage storage);
}
