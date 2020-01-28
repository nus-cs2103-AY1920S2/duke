/**
 * Encapsulates a "list" command from the user.
 * The "list" command takes in no arguments, and will be rejected by `Parser` if arguments are supplied.
 */
public class ListCommand implements Command {
    /**
     * Constructs a new ListCommand instance.
     */
    public ListCommand() {
    }
    
    /**
     * Prints the tasks in the task list to the UI.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        for (int i = 0; i < tasks.size(); i++) {
            ui.showNumberedEntry(i+1, tasks.getTask(i));
        }
    }
    
    /**
     * Returns false, since "list" is not an exit command.
     * @return false
     */
    public boolean isExit() {
        return false;
    }
}
