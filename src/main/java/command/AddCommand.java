package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * A command object for adding tasks to the list.
 */
public abstract class AddCommand extends Command {
    protected final String taskName;

    /**
     * Constructs a command object to add task to the list.
     * @param taskName The name of the task to be added.
     */
    public AddCommand(String taskName) {
        this.taskName = taskName;
    }

    public abstract String execute(TaskList tasks, Ui ui, Storage storage);
}
