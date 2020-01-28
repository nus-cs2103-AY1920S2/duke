/**
 * Encapsulates a "done" command from the user.
 */
public class DoneCommand implements Command {
    int taskIndex;
    
    /**
     * Constructs a new DoneCommand instance.
     * @param taskIndex 1-index of the task to be marked as done
     */
    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }
    
    /**
     * Marks the taskIndex-th task in the task list as done.
     * @param tasks TaskList object to store tasks
     * @param ui UI object for interfacing with the user
     * @param storage Storage object to read and write TaskList state from files
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = tasks.getTask(taskIndex - 1);
            tasks.markTaskAsDone(taskIndex - 1);
            
            ui.showDoneTaskMessage(task);
        } catch(IndexOutOfBoundsException e) {
            throw new DukeException("Task number must be within the range of current tasks");
        }
    }
    
    /**
     * Returns false, since "done" is not an exit command.
     * @return false
     */
    public boolean isExit() {
        return false;
    }
}
