package duke.pack;

/**
 * Represents a command to add a task.
 */
public class AddCommand extends Command {
    protected Task task;

    /**
     * Creates an add command.
     * @param task the task to be added
     */
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

    /**
     * Executes add command and gets response for the GUI
     * @param tasks TaskList containing all the added tasks
     * @param ui UI that handles interactions with user
     * @param storage Storage that handles updating of tasks in hard disk
     * @return String response for GUI
     * @throws DukeException if task cannot be saved to hard disk
     */
    public String getResponse(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(task);
        storage.save(tasks);

        int taskNum = tasks.getSize();
        String count = "You now have " + taskNum + " tasks in your list!";

        String resp = "Alright! I have added: \n" + task + "\n" + count;

        return resp;
    }

    /**
     * Indicates whether command is exit.
     * @return boolean true if it is an exit command, else false
     */
    public Boolean isExit() {
        return false;
    }

}
