import java.util.ArrayList;

/**
 * Represents a Command object created when the user inputs the command to list the tasks in the task list.
 */
public class ListCommand extends Command {
    public ListCommand(String command) {
        super(command);
    }

    /**
     * Prints the user's entire current task list.
     * If the task list is empty, the program will flag to the user that the task list is empty.
     * @param tasks TaskList object from the driver Duke object.
     * @param ui Ui object from the driver Duke object.
     * @param storage Storage object from the driver Duke object.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        // Get the task list from tasks
        ArrayList<Task> taskList = tasks.getTaskList();
        return ui.printTaskList(taskList);
    }
}
