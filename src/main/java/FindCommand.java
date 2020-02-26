import java.util.ArrayList;

/**
 * Represents a Command object that will be created when
 * the user inputs the command to find tasks using a keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String input) {
        super(input);
        this.keyword = input.split(" ")[1];
    }

    /**
     * Executes the command to find tasks that match the given keyword in the task list.
     * Flags to the user if the task does not exist in the task list.
     * @param tasks TaskList object from the driver Duke object.
     * @param ui Ui object from the driver Duke object.
     * @param storage Storage object from the driver Duke object
     * @return String of the output to be returned to the user when tasks matching
     *         the keyword are found.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder sb = new StringBuilder();
        ArrayList<Task> taskList = tasks.getTaskList();
        ArrayList<Task> foundList = new ArrayList<>();
        for (Task t: taskList) {
            String taskString = t.toString();
            // Split the task string by space
            String[] splitString = taskString.split(" ");
            // Iterate through the array and match the word
            for (String s: splitString) {
                if (s.equals(this.keyword)) {
                    // If the keyword is found, add to the foundList
                    foundList.add(t);
                    break;
                }
            }
        }
        return ui.findTaskSuccess(foundList);
    }
}
