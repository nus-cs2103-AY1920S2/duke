package duke.command;

import duke.task.Storage;
import duke.task.TaskList;

/**
 * Represents a ExitCommand.
 * Used to execute the exit command.
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit command.
     *
     * @param tasks TaskList object that contains the tasks of the application.
     * @param storage storage object for the retrieval/saving of tasks.
     * @return The program's output.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "Wait! Don't leave me! Please bring me along!";
    }
}
