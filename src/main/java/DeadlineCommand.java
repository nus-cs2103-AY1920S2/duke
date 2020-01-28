import java.time.LocalDate;

/**
 * Encapsulates a "deadline" command from the user.
 */
public class DeadlineCommand implements Command {
    private String description;
    private LocalDate deadline;
    
    /**
     * Constructs a new DeadlineCommand instance.
     * @param description Task description
     * @param deadline Deadline for the task
     */
    public DeadlineCommand(String description, LocalDate deadline) {
        this.description = description;
        this.deadline = deadline;
    }
    
    /**
     * Adds a new Deadline task specified by this DeadlineCommand into the task list.
     * @param tasks TaskList object to store tasks
     * @param ui UI object for interfacing with the user
     * @param storage Storage object to read and write TaskList state from files
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new Deadline(description, deadline);
        tasks.addTask(newTask);
        
        ui.showAddTaskMessage(newTask, tasks.size());
    }
    
    /**
     * Returns false, since "deadline" is not an exit command.
     * @return false
     */
    public boolean isExit() {
        return false;
    }
}
