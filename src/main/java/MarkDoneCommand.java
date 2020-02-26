/**
 * Represents the Command object that is created when the user inputs the command to mark a particular task as done.
 */
public class MarkDoneCommand extends Command {
    private int taskNumber;

    public MarkDoneCommand(String command, int taskNumber) {
        super(command);
        this.taskNumber = taskNumber;
    }

    /**
     * Sets the task in the task list, indicated by taskNumber, as done.
     * @param tasks TaskList object from the driver Duke object.
     * @param ui Ui object from the driver Duke object.
     * @param storage Storage object from the driver Duke object.
     * @throws DukeException Exception thrown when the task to be marked done does not exist in the task list.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            // Mark the task as done
            Task taskDone = tasks.markTaskDone(taskNumber);
            // Update the file by saving it
            storage.saveFile(tasks.getTaskList());
            // Update the user
            return ui.markDoneSuccess(taskDone);
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }

}
