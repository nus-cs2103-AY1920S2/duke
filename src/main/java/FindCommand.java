import java.util.ArrayList;

/**
 * FindCommand is a sub-class of Command.
 * It handles the finding of tasks with the keyword in the task list.
 */
public class FindCommand extends Command {
    String keyword;

    FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * This method iterates through the task list and find tasks with the name containing the keyword.
     *
     * @param tasks The task list to be searched.
     * @param ui The UI class to print out the messages.
     * @param storage The Storage class.
     */
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task t : tasks.getTaskList()) {
            if (t.getTaskName().contains(keyword)) {
                foundTasks.add(t);
            }
        }
        ui.printFoundTasks(foundTasks);
    }

    public boolean isExit() {
        return false;
    }

}
