/**
 * AddCommand is a sub-class of Command.
 * It handles adding the tasks into the task list.
 */
public class AddCommand extends Command {
    Task task;

    AddCommand(Task task) {
        this.task = task;
    }

    /**
     * This method firstly checks if the task being added currently exists in the list.
     * If not, add it in.
     * If yes, do not add it in.
     *
     * @param tasks List of tasks to be added in.
     * @param ui The UI class to print out the messages.
     * @param storage The Storage class.
     */
    public void execute(TaskList tasks, UI ui, Storage storage) {
        if (checkDuplicate(tasks, task)) {
            ui.printDuplicate(task);
        } else {
            tasks.getTaskList().add(task);
            ui.printAdd(task, tasks);
        }
    }

    /**
     * This method checks the the task exists in the list.
     *
     * @param tasks Lists of tasks to be checked against.
     * @param task The task being checked.
     * @return Returns true if there already exists the task in the task list, else returns false.
     */
    private boolean checkDuplicate(TaskList tasks, Task task) {
        boolean bool = false;
        for (Task t : tasks.getTaskList()) {
            if (t.getTaskName().equals(task.getTaskName())) {
                bool = true;
            }
        }
        return bool;
    }

    public boolean isExit() {
        return false;
    }
}
