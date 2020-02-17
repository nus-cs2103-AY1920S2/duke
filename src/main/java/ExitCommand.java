/**
 * ExitCommand is a sub-class of Command
 * It handles the saving of tasks in the task list and exits the program.
 */
public class ExitCommand extends Command {

    /**
     * This method saves the tasks in the task list.
     *
     * @param tasks The task list to be saved.
     * @param ui The UI class to print out the messages.
     * @param storage The Storage class.
     */
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.printBye();
        try {
            storage.writeList(tasks);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean isExit() {
        return true;
    }
}
