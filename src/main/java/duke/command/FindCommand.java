package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Finds all tasks with the given keyword.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Constructs a FindCommand object.
     *
     * @param keyword the keyword to search for.
     */
    public FindCommand(String keyword) {
        super(false);
        this.keyword = keyword;
    }

    /**
     * Executes FindCommand by searching tasks and list tasks that contains the keyword in the description.
     *
     * @param taskList   TaskList of Duke.
     * @param ui      The user interface.
     * @param storage To load from and save to the disk.
     * @return List of tasks that contains keyword.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return taskList.find(keyword);
    }
}
