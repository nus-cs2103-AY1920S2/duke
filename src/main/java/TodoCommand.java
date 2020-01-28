/**
 * Encapsulates a "todo" command from the user.
 */
public class TodoCommand implements Command {
    String description;
    
    /**
     * Constructs a new TodoCommand instance.
     * @param description Task description
     */
    public TodoCommand(String description) {
        this.description = description;
    }
    
    /**
     * Adds a new Todo task specified by this TodoCommand into the task list.
     * @param tasks TaskList object to store tasks
     * @param ui UI object for interfacing with the user
     * @param storage Storage object to read and write TaskList state from files
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new Todo(description);
        tasks.addTask(newTask);
        
        ui.showAddTaskMessage(newTask, tasks.size());
    }
    
    /**
     * Returns false, since "todo" is not an exit command.
     * @return false
     */
    public boolean isExit() {
        return false;
    }
}
