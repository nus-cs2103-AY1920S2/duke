package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Find tasks from the TaskList with the specified keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     * @param keyword The keyword to filter.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds tasks with the specified keyword and prints the tasks.
     * @param tasks The TaskList containing the tasks.
     * @param ui The Ui that interacts with the user.
     * @param storage The Storage to load and save tasks into the data file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList filteredTasks = tasks.find(keyword);
        ui.showFindTasks(filteredTasks);
    }
}
