/**
 * DeleteCommand is a sub-class of Command.
 * It handles the deletion of the task in the task list.
 */
public class DeleteCommand extends Command {

    private int taskNumber;

    DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * This method firstly gets the task to be deleted via the taskNumber.
     * It then removes the task from the task list.
     *
     * @param tasks The task list to delete the task from.
     * @param ui The UI class to print out the messages.
     * @param storage The Storage class.
     */
    public void execute(TaskList tasks, UI ui, Storage storage) {
        Task task = tasks.getTaskList().get(taskNumber - 1);
        tasks.getTaskList().remove(task);
        ui.printDelete(task, taskNumber, tasks);
    }

    public boolean isExit() {
        return false;
    }
}
