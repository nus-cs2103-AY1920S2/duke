package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Lists the tasks in the TaskList.
 */
public class ListCommand extends Command {
    /**
     * Prints the tasks in the specified TaskList.
     *
     * @param tasks The TaskList containing the tasks.
     * @return A string with the message to be printed.
     */
    @Override
    public String execute(TaskList tasks) {
        if (tasks.isEmpty()) {
            return Ui.MESSAGE_NO_TASK;
        } else {
            return Ui.showToUser(Ui.MESSAGE_LIST, tasks.toString());
        }
    }
}
