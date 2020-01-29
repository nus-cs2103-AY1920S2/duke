package duke.pack;

/**
 * Represents a command to add a task.
 */
public class AddCommand extends Command {
    protected Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add command and adds task to the TaskList.
     * @param tasks TaskList containing all the added tasks
     * @param ui UI that handles interactions with user
     * @param storage Storage that handles updating of tasks in hard disk
     * @throws DukeException if task cannot be saved to hard disk
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(task);
        storage.save(tasks);
        ui.showAdd(task);
        ui.showCount(tasks);
    }

    public Boolean isExit() {
        return false;
    }

}
