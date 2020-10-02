/**
 * ListCommand is a sub-class of Command.
 * It handles the printing of tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * This method simply prints out all the tasks in the task list.
     *
     * @param tasks The task list of tasks to be printed.
     * @param ui The UI class to print out the messages.
     * @param storage The Storage class.
     */
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.printList(tasks);
    }

    public boolean isExit() {
        return false;
    }
}
