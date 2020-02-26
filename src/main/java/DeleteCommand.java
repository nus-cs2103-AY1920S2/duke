/**
 * Represents a Command object that will be created when
 * the user inputs the command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    int taskNumber;

    public DeleteCommand(String command, int taskNumber) {
        super(command);
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the command to delete the task indicated by the taskNumber from the user's task list.
     * Throws an exception if the user tries to execute this command when the task list is empty, or if the
     * task does not exist in the task list.
     * @param tasks TaskList object from the driver Duke object.
     * @param ui Ui object from the driver Duke object.
     * @param storage Storage object from the driver Duke object.
     * @throws DukeException Exception thrown by the method deleteTask if the task list is empty,
     *     or the task to delete does not exist.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            // Remove the task from the task list in tasks
            Task deletedTask = tasks.deleteTask(taskNumber);
            // Update the file by saving it
            storage.saveFile(tasks.getTaskList());
            // Update user on the deletion of the task
            return ui.taskDeleteSuccess(deletedTask, tasks.getTaskList().size());
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
