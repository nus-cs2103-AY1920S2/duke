package duke.command;

import duke.task.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a ListCommand.
 * Used to execute the list command.
 */
public class ListCommand implements Command {
    private static final String EMPTY_TASK_LIST_MESSAGE = "I may or may not have exploded all your tasks"
            + " in your list as it is empty.\n";

    /**
     * Executes the list command.
     *
     * @param tasks TaskList object that contains the tasks of the application.
     * @param ui Ui object for the command to interact with the user.
     * @param storage storage object for the retrieval/saving of tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.getSize() > 0) {
            ui.addMessage("Here are the tasks in your list:\n" + tasks);
        } else {
            ui.addMessage(EMPTY_TASK_LIST_MESSAGE);
        }
    }
}
