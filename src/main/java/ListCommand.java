import java.util.ArrayList;

public class ListCommand extends Command {
    public ListCommand(String command) {
        super(command);
    }

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
