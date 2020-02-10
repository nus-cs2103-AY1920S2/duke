package duke.command;

import duke.task.Storage;
import duke.task.TaskList;

/**
 * Represents a Command.
 * This is an interface meant as a template for the commands.
 */
public interface Command {
    /**
     * Method to execute the command.
     * Must be overridden by implementing classes.
     *
     * @param tasks TaskList object that contains the tasks of the application.
     * @param storage storage object for the retrieval/saving of tasks.
     * @return The program's output.
     */
    String execute(TaskList tasks, Storage storage);
}
