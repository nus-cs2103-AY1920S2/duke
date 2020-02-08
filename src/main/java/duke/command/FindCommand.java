package duke.command;

import duke.Storage;
import duke.TaskList;
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
     * @param ui The Ui that interacts with the user.
     * @param storage The Storage to load and save tasks into the data file.
     * @return A string with the message to be printed.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList filteredTasks = tasks.find(keyword);
        if (filteredTasks.isEmpty()) {
            return ui.showToUser("There are no matching tasks in your list.");
        } else {
            return ui.showToUser("Here are the matching tasks in your list:", filteredTasks.toString());
        }
    }
}
