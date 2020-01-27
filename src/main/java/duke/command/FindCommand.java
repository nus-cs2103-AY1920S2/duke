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
     * Constructor of FindCommand.
     *
     * @param keyword the keyword to search for.
     */
    public FindCommand(String keyword) {
        super(false);
        this.keyword = keyword;
    }

    /**
     * Executes FindCommand by searching tasks and list tasks that contains the keyword in the description.
     * @param tasks TaskList of Duke.
     * @param ui The user interface.
     * @param storage To load from and save to the disk.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.find(keyword);
    }
}
