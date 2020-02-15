package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Find tasks from the TaskList with the specified keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to filter.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds tasks with the specified keyword and returns a string representation of the tasks.
     *
     * @param tasks The TaskList containing the tasks.
     * @return A string with the message to be printed.
     */
    @Override
    public String execute(TaskList tasks) {
        TaskList filteredTasks = tasks.find(keyword);
        if (filteredTasks.isEmpty()) {
            return Ui.MESSAGE_NO_MATCHING_TASK;
        } else {
            return Ui.showToUser(Ui.MESSAGE_FIND, filteredTasks.toString());
        }
    }
}
