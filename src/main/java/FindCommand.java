/**
 * Represents a command to search for tasks containing a specified keyword,
 * to be executed later.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructor for FindCommand that takes in a search keyword.
     * @param keyword the search keyword
     */
    public FindCommand (String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds the tasks containing the keyword, and calls the UI to display them.
     * @param tasks the task list to operate on
     * @param ui the user interface
     * @param storage the storage for the task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList lists = tasks.getRelevantTasks(keyword);
        ui.showFoundTasks(lists);
    }
}
