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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // Get the task list from tasks
        ArrayList<Task> taskList = tasks.getTaskList();
        ui.printLine();
        // Print out the list
        if (taskList.size() == 0) {
            System.out.println("\t Your task list is empty!");
        } else {
            System.out.println("\t Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                // Print task number first
                System.out.print("\t " + (i + 1) + ".");
                // Get the task and print it
                Task currTask = taskList.get(i);
                System.out.println(currTask);
            }
        }
        ui.printLine();
    }
}
