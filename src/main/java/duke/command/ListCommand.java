package duke.command;

import duke.task.Storage;
import duke.task.TaskList;

/**
 * Represents a ListCommand.
 * Used to execute the list command.
 */
public class ListCommand implements Command {
    private static final String EMPTY_TASK_LIST_MESSAGE = "I may or may not have exploded all your tasks"
            + " in your list as it is empty.";

    /**
     * Executes the list command.
     *
     * @param tasks TaskList object that contains the tasks of the application.
     * @param storage storage object for the retrieval/saving of tasks.
     * @return The program's output.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        if (tasks.getSize() > 0) {
            return "Here are the tasks in your list:\n" + tasks;
        } else {
            return EMPTY_TASK_LIST_MESSAGE;
        }
    }
}
