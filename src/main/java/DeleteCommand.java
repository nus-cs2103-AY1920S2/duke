/**
 * Encapsulates a "delete" command from the user.
 */
public class DeleteCommand implements Command {
    private int taskIndex;
    
    /**
     * Constructs a new DeleteCommand instance.
     * @param taskIndex 1-index of the task to be deleted
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }
    
    /**
     * Removes the taskIndex-th task from the task list.
     * @param tasks TaskList object to store tasks
     * @param ui UI object for interfacing with the user
     * @param storage Storage object to read and write TaskList state from files
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = tasks.removeTask(taskIndex - 1);
            ui.showRemoveTaskMessage(task, tasks.size());
        } catch(IndexOutOfBoundsException e) {
            throw new DukeException("Task number must be within the range of current tasks");
        }
    }
    
    /**
     * Returns false, since "delete" is not an exit command.
     * @return false
     */
    public boolean isExit() {
        return false;
    }
}
