package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * List tasks from the task list.
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
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < tasks.getTasks().size(); i++) {
            System.out.println("     " + (i + 1) + ". " + tasks.getTasks().get(i));
        }
    }
}
