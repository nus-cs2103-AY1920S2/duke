package duke.command;

import duke.task.Storage;
import duke.task.TaskList;

/**
 * Represents a ExitCommand.
 * Used to execute the exit command.
 */
public class ExitCommand implements Command {
    private static final String EXIT_MESSAGE = "Wait! Don't leave me! Please bring me along!";

    /**
     * Executes the exit command.
     *
     * @param tasks TaskList object that contains the tasks of the application.
     * @param storage storage object for the retrieval/saving of tasks.
     * @return The program's output.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return EXIT_MESSAGE;
    }
}
