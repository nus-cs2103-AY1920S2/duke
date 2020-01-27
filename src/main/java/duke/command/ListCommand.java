package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Lists tasks from the task list.
 */
public class ListCommand extends Command {

    /**
     * Constructor of ListCommand.
     */
    public ListCommand() {
        super(false);
    }

    /**
     * Executes the ListCommand by listing all the tasks in the task list.
     *
     * @param tasks TaskList of Duke.
     * @param ui The user interface.
     * @param storage To load from and save to the disk.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.listTasks();
    }
}
